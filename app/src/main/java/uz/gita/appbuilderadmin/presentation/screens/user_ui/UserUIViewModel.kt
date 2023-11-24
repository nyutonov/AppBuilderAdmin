package uz.gita.appbuilderadmin.presentation.screens.user_ui

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
import javax.inject.Inject

@HiltViewModel
class UserUIViewModel @Inject constructor(
    private val direction: UserUIContract.Direction,
    private val repository: Repository
) : UserUIContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(UserUIContract.UIState())

    override fun onEventDispatcher(intent: UserUIContract.Intent) {
        when (intent) {
            is UserUIContract.Intent.ClickAddComponents -> {
                viewModelScope.launch { direction.moveToConstructor(intent.name) }
            }

            is UserUIContract.Intent.LoadData -> {
                repository.getAllData(intent.name)
                    .onStart { uiState.update { it.copy(loader = true) } }
                    .onEach { list ->
                        uiState.update { it.copy(components = list, loader = false) }
                    }
                    .launchIn(viewModelScope)
            }

            is UserUIContract.Intent.SetName -> {
                uiState.update { it.copy(name = intent.name) }
            }
            is UserUIContract.Intent.DeleteComponents->{
                viewModelScope.launch {
                    repository.deleteComponent(intent.componentsModel,intent.name)
                }
            }
        }
    }
}