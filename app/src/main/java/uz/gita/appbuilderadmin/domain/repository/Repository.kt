package uz.gita.appbuilderadmin.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.UserModel
import uz.gita.appbuilderadmin.data.model.VisibilityTypeModule

interface Repository {

    fun addUser(userModel: UserModel) : Flow<Boolean>
    fun getAllUsers() : Flow<List<UserModel>>
    fun getAllData(name : String):Flow<List<ComponentsModel>>
    suspend fun addComponent(name: String, component: ComponentsModel)

    fun uploadImage(imageUri: Uri): Flow<Boolean>

    suspend fun deleteComponent(component: ComponentsModel,name:String)

    fun addVisibilityModule(visibilityTypeModule: VisibilityTypeModule)
    fun getAllVisibilityModule() : List<VisibilityTypeModule>
    fun getAllListInputId() : List<String>
    fun getAllSelectorId() : List<String>
    fun getAllMultiSelectorId() : List<String>
    fun getSelectorValueListById(id : String) : List<String>
    fun getMultiSelectorValueListById(id : String) : List<String>
    fun removeAllVisibilityData()
}