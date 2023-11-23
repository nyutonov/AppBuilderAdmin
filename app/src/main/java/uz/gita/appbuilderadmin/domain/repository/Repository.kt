package uz.gita.appbuilderadmin.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.domain.param.UserParam

interface Repository {

    fun addUser(userParam: UserParam) : Flow<Boolean>
    fun getAllUsers() : Flow<List<String>>
    fun getAllData(name : String):Flow<List<ComponentsModel>>
    suspend fun addComponent(name: String, component: ComponentsModel)
}