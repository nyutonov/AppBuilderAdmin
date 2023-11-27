package uz.gita.appbuilderadmin.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.UserModel
import uz.gita.appbuilderadmin.data.model.VisibilityTypeModule
import uz.gita.appbuilderadmin.data.source.local.room.VisibilityDao
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.utils.extensions.getAll
import uz.gita.appbuilderadmin.utils.mapper.toComponentData
import uz.gita.appbuilderadmin.utils.mapper.toData
import uz.gita.appbuilderadmin.utils.mapper.toEntity
import java.util.UUID
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val visibilityDao: VisibilityDao
) : Repository {
    private val firebasePref = Firebase.firestore
    private val firebaseDatabase = Firebase.database
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private lateinit var listVisiblity : List<VisibilityTypeModule>

    init {

        visibilityDao.getAllVisibility()
            .onEach {
                listVisiblity = it.map { it.toData() }
            }
            .launchIn(scope)

    }

    override fun addUser(userModel: UserModel): Flow<Boolean> = callbackFlow {
        val uuid = UUID.randomUUID().toString()
        val user = userModel

        firebaseDatabase.getReference("id").get().addOnSuccessListener {
            var id = it.child("userId").getValue(Int::class.java) ?: 1

            user.id = id
            id += 1

            firebaseDatabase.getReference("id").child("userId").setValue(id)

            firebasePref
                .collection("users")
                .document(uuid)
                .set(user)
                .addOnSuccessListener {
                    trySend(true)
                }
                .addOnFailureListener {
                    trySend(false)
                }
        }

        awaitClose()
    }

    override fun getAllUsers(): Flow<List<UserModel>> = callbackFlow {
        firebasePref
            .collection("users")
            .get().getAll {
                return@getAll UserModel(
                    it.data?.getOrDefault("id", 0).toString().toInt(),
                    it.data?.getOrDefault("name", "") as String,
                    it.data?.getOrDefault("password", "") as String
                )
            }
            .onEach { it.onSuccess { send(it) } }
            .launchIn(scope)

        awaitClose()
    }

    override fun getAllData(name: String): Flow<List<ComponentsModel>> = callbackFlow {
        firebaseDatabase
            .getReference("users")
            .child(name)
            .child("components")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(snapshot.children.map { it.toComponentData() })
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        awaitClose()
    }

    override suspend fun addComponent(name: String, component: ComponentsModel): Unit =
        withContext(Dispatchers.IO) {
            val uuid = UUID.randomUUID().toString()

            firebaseDatabase
                .getReference("users")
                .child(name)
                .child("components")
                .run {
                    this
                        .get()
                        .addOnSuccessListener {
                            var id = it.child("componentId").getValue(Int::class.java) ?: 0

                            id += 1

                            this.child("componentId").setValue(id)

                            this.child(uuid).run {
                                this.child("componentId").setValue(id)

                                this.child("componentsName").setValue(component.componentsName)

                                this.child("input").setValue(component.input)
                                this.child("type").setValue(component.type)
                                this.child("placeHolder").setValue(component.placeHolder)
                                this.child("visibility").setValue(component.visibility)
                                this.child("idVisibility").setValue(component.idVisibility)
                                this.child("operator").setValue(component.operator)
                                this.child("value").setValue(component.value)

                                this.child("isMaxLengthForTextEnabled").setValue(component.isMaxLengthForTextEnabled)
                                this.child("maxLengthForText").setValue(component.maxLengthForText)
                                this.child("isMinLengthForTextEnabled").setValue(component.isMinLengthForTextEnabled)
                                this.child("minLengthForText").setValue(component.minLengthForText)
                                this.child("isMaxValueForNumberEnabled").setValue(component.isMaxValueForNumberEnabled)
                                this.child("maxValueForNumber").setValue(component.maxValueForNumber)
                                this.child("isMinValueForNumberEnabled").setValue(component.isMinValueForNumberEnabled)
                                this.child("minValueForNumber").setValue(component.minValueForNumber)
                                this.child("isRequired").setValue(component.isRequired)

                                this.child("text").setValue(component.text)
                                this.child("color").setValue(component.color)
                                this.child("list").setValue(component.list)

                                this.child("selectorDataQuestion").setValue(component.selectorDataQuestion)
                                this.child("selectorDataAnswers")
                                    .setValue(component.selectorDataAnswers.joinToString(":"))

                                this.child("multiSelectDataQuestion")
                                    .setValue(component.multiSelectDataQuestion)
                                this.child("multiSelectorDataAnswers")
                                    .setValue(component.multiSelectorDataAnswers.joinToString(":"))

                                this.child("datePicker").setValue(component.datePicker)
                                this.child("id").setValue(component.id.ifEmpty { UUID.randomUUID().toString() })
                            }
                        }
                }
        }

    override suspend fun deleteComponent(component: ComponentsModel, name: String) {
        val databaseReference =
            firebaseDatabase.getReference("users").child(name).child("components")
        Log.d("TTT", "deleteComponent components id:${component.key} ")
        databaseReference.child(component.key).removeValue()
            .addOnSuccessListener {
                // Успешно удалено
            }
            .addOnFailureListener { error ->
                // Обработка ошибки
            }
    }

    override fun addVisibilityModule(visibilityTypeModule: VisibilityTypeModule) {
        scope.launch {
            visibilityDao.addVisibility(visibilityTypeModule.toEntity())
        }
    }

    override fun getAllVisibilityModule(): List<VisibilityTypeModule> = listVisiblity

    override fun getAllListInputId(): List<String> {
        val list = ArrayList<String>()
        listVisiblity.forEach {
            if (it.type == "Input") {
                list.add(it.id)
            }
        }
        return list
    }

    override fun getAllSelectorId(): List<String> {
        val list = ArrayList<String>()
        listVisiblity.forEach {
            if (it.type == "Selector")  {
                list.add(it.id)
            }
        }
        return list
    }

    override fun getAllMultiSelectorId(): List<String> {
        val list = ArrayList<String>()
        listVisiblity.forEach {
            if (it.type == "Multi Selector")  {
                list.add(it.id)
            }
        }
        return list
    }

    override fun getSelectorValueListById(id: String): List<String> {
        listVisiblity.forEach {
            if (it.id == id) return it.values
        }
        return listOf()
    }

    override fun getMultiSelectorValueListById(id: String): List<String> {
        listVisiblity.forEach {
            if (it.id == id) return it.values
        }
        return listOf()
    }

    override fun removeAllVisibilityData() {

    }
}