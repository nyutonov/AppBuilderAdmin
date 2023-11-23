package uz.gita.appbuilderadmin.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.domain.param.UserParam
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.utils.extensions.getAll
import uz.gita.appbuilderadmin.utils.extensions.toModel
import uz.gita.appbuilderadmin.utils.mapper.toUserData
import java.util.UUID
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val firebasePref = Firebase.firestore
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private val realtimeDb=FirebaseDatabase.getInstance()

    override fun addUser(userParam: UserParam): Flow<Boolean> = callbackFlow {
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

    override fun getAllUsers(): Flow<List<String>> = callbackFlow {

        firebasePref
            .collection("users")
            .get().getAll {
                return@getAll it.data?.getOrDefault("name", "") as String
            }.onEach {
                it.onSuccess {
                    trySend(it)
                }
            }
            .launchIn(scope)

        awaitClose()
    }

    override fun getAllData(name:String): Flow<List<ComponentsModel>> = callbackFlow {
      realtimeDb.getReference("users").child(name).child("components").addValueEventListener(object :ValueEventListener {
          override fun onDataChange(snapshot: DataSnapshot) {
              trySend(snapshot.children.map { it.toUserData() })
          }

          override fun onCancelled(error: DatabaseError) {

          }

      })
        awaitClose {  }
    }
}