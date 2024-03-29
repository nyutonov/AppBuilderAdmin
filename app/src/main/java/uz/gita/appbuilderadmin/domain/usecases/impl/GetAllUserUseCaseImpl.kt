package uz.gita.appbuilderadmin.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.appbuilderadmin.data.model.UserModel
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.domain.usecases.GetAllUserUseCase
import javax.inject.Inject

class GetAllUserUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetAllUserUseCase {
    override fun invoke(): Flow<List<UserModel>> = repository.getAllUsers()

}