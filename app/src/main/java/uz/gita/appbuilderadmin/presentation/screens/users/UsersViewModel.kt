package uz.gita.appbuilderadmin.presentation.screens.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.domain.usecases.GetAllUserUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase, private val direction: UsersContract.Direction, private val repository: Repository
) : UsersContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UsersContract.UIState())

    init {
        getAllUserUseCase()
            .onStart { uiState.update { it.copy(progressbar = true) } }
            .onEach { users -> uiState.update { it.copy(users = users.sortedBy { it.id }, progressbar = false) } }
            .launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: UsersContract.Intent) {
        when (intent) {
            is UsersContract.Intent.ClickUser -> {
                viewModelScope.launch {
                    direction.moveToUserUI(intent.name)
                }
            }

            UsersContract.Intent.ClickAddUser -> {
                viewModelScope.launch { direction.moveToRegister() }
            }

            is UsersContract.Intent.DeleteUser -> {
                repository
                    .deleteUser(intent.key, intent.name)
                    .onEach {  }
                    .launchIn(viewModelScope)
            }
        }
    }
}