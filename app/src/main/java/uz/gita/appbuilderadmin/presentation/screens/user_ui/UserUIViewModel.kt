package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserUIViewModel @Inject constructor(
    private val direction: UserUIContract.Direction
) : UserUIContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UserUIContract.UIState())

    override fun onEventDispatcher(intent: UserUIContract.Intent) {
        when (intent) {
            is UserUIContract.Intent.ClickAddComponents -> {
                viewModelScope.launch { direction.moveToConstructor(uiState.value.name) }
            }

            is UserUIContract.Intent.SetName -> {
                uiState.update { it.copy(name = intent.name) }
            }
        }
    }
}