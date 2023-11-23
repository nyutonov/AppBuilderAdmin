package uz.gita.appbuilderadmin.presentation.screens.user_ui

import kotlinx.coroutines.flow.StateFlow
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.utils.navigator.AppNavigator
import javax.inject.Inject

interface UserUIContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>

        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        data class ClickAddComponents(
            val name: String
        ) : Intent
    }

    data class UIState(
        val components: List<ComponentsModel> = listOf()
    )

    interface Direction {
        suspend fun moveToConstructor(userName:String)
        class Direction @Inject constructor(private val appNavigator: AppNavigator):UserUIContract.Direction{
            override suspend fun moveToConstructor(userName:String) {
//                appNavigator.navigateTo()
            }

        }
    }
}