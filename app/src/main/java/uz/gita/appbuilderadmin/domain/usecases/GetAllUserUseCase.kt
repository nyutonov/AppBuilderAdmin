package uz.gita.appbuilderadmin.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.UserModel

interface GetAllUserUseCase {

    operator fun invoke() : Flow<List<UserModel>>

}