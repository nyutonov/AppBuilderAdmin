package uz.gita.appbuilderadmin.domain.usecases.impl

import uz.gita.appbuilderadmin.domain.param.UserParam
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.domain.usecases.AddUserUseCase
import javax.inject.Inject

class AddUserUseCaseImpl @Inject constructor(
    private val repository: Repository
) : AddUserUseCase {
    override fun invoke(userParam: UserParam) = repository.addUser(userParam)
}