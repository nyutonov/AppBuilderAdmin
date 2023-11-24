package uz.gita.appbuilderadmin.domain.repository

import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.UserModel

interface Repository {

    fun addUser(userModel: UserModel) : Flow<Boolean>
    fun getAllUsers() : Flow<List<UserModel>>
    fun getAllData(name : String):Flow<List<ComponentsModel>>
    suspend fun addComponent(name: String, component: ComponentsModel)

    suspend fun deleteComponent(component: ComponentsModel,name:String)
}