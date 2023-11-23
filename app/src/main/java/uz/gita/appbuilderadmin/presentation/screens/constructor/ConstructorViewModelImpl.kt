package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ConstructorViewModelImpl @Inject constructor(
    private val repository: Repository ,
    private val direction : ConstructorDirection
) : ViewModel() , ConstructorContract.ViewModel{
    override val uiState = MutableStateFlow(ConstructorContract.UiState())
    private var name = ""

    private fun reduce(block : (ConstructorContract.UiState) -> ConstructorContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatchers(intent: ConstructorContract.Intent) {
        when(intent) {

            is ConstructorContract.Intent.ChangingPlaceholder -> {
                reduce { it.copy(placeHolder = intent.placeholder) }
            }

            is ConstructorContract.Intent.EnteringName -> {
                name = intent.name
                reduce { it.copy(name = intent.name) }
            }

            is ConstructorContract.Intent.ChangingSelectedComponent -> {
                reduce { it.copy(selectedComponent = intent.component) }
            }

            is ConstructorContract.Intent.ChangingSelectedInputType -> {
                Log.d("TTT", intent.type)
                reduce { it.copy(selectedInputType = intent.type) }
            }

            is ConstructorContract.Intent.ChangingTextValue -> {
                reduce { it.copy(textValue = intent.value) }
            }

            ConstructorContract.Intent.ClickCreateButton -> {
                viewModelScope.launch {
                    uiState.value.apply {
                        repository.addComponent(name , ComponentsModel(
                            selectedComponent ,
                            "" ,
                            selectedInputType ,
                            placeHolder ,
                            textValue ,
                            color = 0xFF0F1C2 ,
                            selectorDataAnswers = listOf(
                                "empty1" ,
                                "empty2" ,
                                "empty3" ,
                                "empty4" ,
                            )
                        )
                        )
                    }
                    direction.back()
                }
            }

        }
    }
}