package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserUiViewModelImpl @Inject constructor(private val direction: UserUIContract.Direction) : ViewModel(),
    UserUIContract.ViewModel {
    override val uiState = MutableStateFlow(UserUIContract.UIState())

    override fun onEventDispatcher(intent: UserUIContract.Intent) {
        when (intent) {
            is UserUIContract.Intent.ClickAddComponents -> {
                viewModelScope.launch {
                    direction.moveToConstructor(intent.name)
                }
            }
        }
    }
}