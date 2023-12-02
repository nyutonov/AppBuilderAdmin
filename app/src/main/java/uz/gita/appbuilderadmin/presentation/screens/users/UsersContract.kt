package uz.gita.appbuilderadmin.presentation.screens.users

import com.google.android.datatransport.cct.StringMerger
import kotlinx.coroutines.flow.StateFlow
import uz.gita.appbuilderadmin.data.model.UserModel

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

        data class ClickDelete(
            val userModel: UserModel
        ):Intent
        object  Load:Intent
        data class DeleteUser(
            val key: String,
            val name: String
        ) : Intent
    }

    data class UIState(
        val users: List<UserModel> = listOf(),
        val progressbar: Boolean = true
    )

    interface Direction {
        suspend fun moveToRegister()
        suspend fun moveToUserUI(name: String)
    }
}