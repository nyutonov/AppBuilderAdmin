package uz.gita.appbuilderadmin.presentation.screens.users

import uz.gita.appbuilderadmin.presentation.screens.register.RegisterScreen
import uz.gita.appbuilderadmin.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : UsersContract.Direction {
    override suspend fun moveToRegister() {
        appNavigator.navigateTo(RegisterScreen())
    }

    override suspend fun moveToUserUI(name: String) {

    }
}