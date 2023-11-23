package uz.gita.appbuilderadmin.presentation.screens.user_ui

import kotlinx.coroutines.flow.StateFlow
import uz.gita.appbuilderadmin.data.model.ComponentsModel

interface UserUIContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>

        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        data class ClickAddComponents(
            val name: String
        ) : Intent
       data class LoadData(
           val name:String
       ):Intent
        data class SetName(
            val name: String
        ) : Intent
    }

    data class UIState(
        var name: String = "",
        val components: List<ComponentsModel> = listOf()
    )

    interface Direction {
        suspend fun moveToConstructor(name: String)
    }
}