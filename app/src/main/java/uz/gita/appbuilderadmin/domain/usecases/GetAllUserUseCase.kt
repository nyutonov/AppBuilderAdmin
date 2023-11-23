package uz.gita.appbuilderadmin.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.domain.param.UserParam

interface GetAllUserUseCase {

    operator fun invoke() : Flow<List<UserParam>>

}