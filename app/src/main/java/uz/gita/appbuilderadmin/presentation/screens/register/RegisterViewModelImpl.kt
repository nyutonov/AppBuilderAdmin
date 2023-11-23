package uz.gita.appbuilderadmin.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.domain.param.UserParam
import uz.gita.appbuilderadmin.domain.usecases.AddUserUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val addUserUseCase: AddUserUseCase ,
    private val direction: RegisterDirection
) : RegisterContract.ViewModel , ViewModel(){

    override val uiState = MutableStateFlow(RegisterContract.UiState())
    private var name = ""
    private var password = ""

    fun reduce(block : (RegisterContract.UiState) -> RegisterContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatchers(intent: RegisterContract.Intent) {
        when(intent) {
            is RegisterContract.Intent.ChangingName -> {
                name = intent.name
                reduce { it.copy(name = name) }
            }

            is RegisterContract.Intent.ChangingPassword -> {
                password = intent.password
                reduce { it.copy(password = password) }
            }

            RegisterContract.Intent.ChangePasswordState -> {
                reduce { it.copy(passwordState = !uiState.value.passwordState) }
            }

            RegisterContract.Intent.ClickRegisterButton -> {
                if (name.isNotEmpty() && password.isNotEmpty()) {
                    viewModelScope.launch {
                        addUserUseCase(UserParam(name , password))
                            .onEach {
                                if (it) {
                                    direction.back()
                                }
                            }
                            .collect()
                    }
                }
            }

        }
    }


}