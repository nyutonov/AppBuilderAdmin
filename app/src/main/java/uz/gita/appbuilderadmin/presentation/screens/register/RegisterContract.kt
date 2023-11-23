package uz.gita.appbuilderadmin.presentation.screens.register

import kotlinx.coroutines.flow.StateFlow

interface RegisterContract {

    interface ViewModel {

        val uiState : StateFlow<UiState>

        fun onEventDispatchers(intent : Intent)

    }

    data class UiState(
        val name : String = "",
        val password : String = "" ,
        val passwordState : Boolean = false,
        val isLoading: Boolean = false
    )

    interface Intent {

        data class ChangingName(
            val name : String
        ) : Intent

        data class ChangingPassword(
            val password : String
        ) : Intent

        object ChangePasswordState : Intent
        object ClickRegisterButton : Intent

    }

}