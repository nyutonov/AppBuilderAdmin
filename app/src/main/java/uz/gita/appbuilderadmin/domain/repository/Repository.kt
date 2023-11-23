package uz.gita.appbuilderadmin.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.domain.param.UserParam

interface Repository {

    fun addUser(userParam: UserParam) : Flow<Boolean>
    fun getAllUsers() : Flow<List<UserParam>>

}