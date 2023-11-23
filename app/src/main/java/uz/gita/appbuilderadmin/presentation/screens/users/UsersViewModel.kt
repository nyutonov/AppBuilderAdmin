package uz.gita.appbuilderadmin.presentation.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.domain.usecases.GetAllUserUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val direction: UsersContract.Direction
) : UsersContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UsersContract.UIState())

    init {
        getAllUserUseCase()
            .onEach { users -> uiState.update { it.copy(users = users) } }
            .launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: UsersContract.Intent) {
        when (intent) {
            is UsersContract.Intent.ClickUser -> {
                //...
            }

            UsersContract.Intent.ClickAddUser -> {
                viewModelScope.launch { direction.moveToRegister() }
            }
        }
    }
}