package uz.gita.appbuilderadmin.presentation.screens.users

import kotlinx.coroutines.flow.StateFlow

interface UsersContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>

        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        data class ClickUser(
            val name: String
        ) : Intent

        object ClickAddUser : Intent
    }

    data class UIState(
        val users: List<String> = listOf(),
        val progressbar: Boolean = true
    )

    interface Direction {
        suspend fun moveToRegister()
        suspend fun moveToUserUI(name: String)
    }
}