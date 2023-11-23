package uz.gita.appbuilderadmin.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.domain.param.UserParam

interface AddUserUseCase {

    operator fun invoke(userParam: UserParam) : Flow<Boolean>

}