package uz.gita.appbuilderadmin.presentation.screens.user_ui

import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorScreen
import uz.gita.appbuilderadmin.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserUIDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : UserUIContract.Direction {
    override suspend fun moveToConstructor(name: String) {
        appNavigator.navigateTo(ConstructorScreen(name))
    }
}