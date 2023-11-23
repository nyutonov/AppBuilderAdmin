package uz.gita.appbuilderadmin.presentation.screens.register

import uz.gita.appbuilderadmin.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface RegisterDirection {

    suspend fun back()

}

@Singleton
class RegisterDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : RegisterDirection {
    override suspend fun back() {
        appNavigator.back()
    }

}