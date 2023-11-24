package uz.gita.appbuilderadmin.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
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
import kotlinx.coroutines.withContext
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
    private val firebaseDatabase = Firebase.database
    private val scope = CoroutineScope(Dispatchers.IO + Job())

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
            .get().getAll { return@getAll it.data?.getOrDefault("name", "") as String }
            .onEach { it.onSuccess { trySend(it) } }
            .launchIn(scope)

        awaitClose()
    }

    override fun getAllData(name: String): Flow<List<ComponentsModel>> = callbackFlow {
        firebaseDatabase
            .getReference("users")
            .child(name)
            .child("components")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) { trySend(snapshot.children.map { it.toUserData() }) }

          override fun onCancelled(error: DatabaseError) {

          }
      }
      )
        awaitClose()
    }

    override suspend fun addComponent(name: String, component: ComponentsModel): Unit =
        withContext(Dispatchers.IO) {
            firebaseDatabase
                .getReference("users")
                .child(name)
                .child("components")
                .child(UUID.randomUUID().toString())
                .run {
                    this.child("componentsName").setValue(component.componentsName)

                this.child("input").setValue(component.input)
                this.child("type").setValue(component.type)
                this.child("placeholder").setValue(component.placeHolder)
                this.child("visibility").setValue(component.visibility)
                this.child("idVisibility").setValue(component.idVisibility)
                this.child("operator").setValue(component.operator)
                this.child("value").setValue(component.value)

                    this.child("text").setValue(component.text)
                    this.child("color").setValue(component.color)

                    this.child("selectorDataQuestion").setValue(component.selectorDataQuestion)
                    this.child("selectorDataAnswers")
                        .setValue(component.selectorDataAnswers.joinToString(":"))

                    this.child("multiSelectDataQuestion")
                        .setValue(component.multiSelectDataQuestion)
                    this.child("multiSelectorDataAnswers")
                        .setValue(component.multiSelectorDataAnswers.joinToString(":"))

                    this.child("datePicker").setValue(component.datePicker)
                    this.child("id").setValue(component.id)
                }
        }

    override suspend fun deleteComponent(component: ComponentsModel, name: String) {
        val databaseReference = firebaseDatabase.getReference("users").child(name).child("components")
        Log.d("TTT", "deleteComponent components id:${component.id} ")
        databaseReference.child(component.id).removeValue()
            .addOnSuccessListener {
                // Успешно удалено
            }
    }
}