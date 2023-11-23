package uz.gita.appbuilderadmin.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.appbuilderadmin.data.model.UserModel
import uz.gita.appbuilderadmin.domain.param.UserParam
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.utils.extensions.getAll
import uz.gita.appbuilderadmin.utils.extensions.toModel
import uz.gita.appbuilderadmin.utils.extensions.toParam
import java.util.UUID
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val firebasePref = Firebase.firestore
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    override fun addUser(userParam: UserParam) : Flow<Boolean> = callbackFlow {
        val uuid = UUID.randomUUID().toString()
        val userModel = userParam.toModel()
        firebasePref
            .collection("users")
            .document(uuid)
            .set(userModel)
            .addOnSuccessListener {
                trySend(true)
            }
            .addOnFailureListener {
                trySend(false)
            }

        awaitClose()
    }

    override fun getAllUsers(): Flow<List<UserParam>> = callbackFlow {

        firebasePref
            .collection("users")
            .get().getAll {
                return@getAll UserModel(
                    it.data?.getOrDefault("name" , "") as String,
                    it.data?.getOrDefault("password" , "") as String
                )
            }.onEach {
                it.onSuccess {
                    trySend(it.map { it.toParam() })
                }
            }
            .launchIn(scope)

        awaitClose()
    }

}