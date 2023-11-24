package uz.gita.appbuilderadmin.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.UserModel

interface AddUserUseCase {

    operator fun invoke(userParam: UserModel) : Flow<Boolean>

}