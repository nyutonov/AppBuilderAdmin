package uz.gita.appbuilderadmin.presentation.screens.constructor

import uz.gita.appbuilderadmin.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ConstructorDirection {

    suspend fun back()

}

@Singleton
class ConstructorDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ConstructorDirection{
    override suspend fun back() {
        appNavigator.back()
    }

}