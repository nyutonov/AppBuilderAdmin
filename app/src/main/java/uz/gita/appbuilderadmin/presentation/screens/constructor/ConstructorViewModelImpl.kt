package uz.gita.appbuilderadmin.presentation.screens.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.VisibilityModule
import uz.gita.appbuilderadmin.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ConstructorViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val direction: ConstructorDirection
) : ViewModel(), ConstructorContract.ViewModel {
    override val uiState = MutableStateFlow(ConstructorContract.UiState())
    private var name = ""
    private var list = ArrayList<VisibilityModule>()

    private fun reduce(block: (ConstructorContract.UiState) -> ConstructorContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatchers(intent: ConstructorContract.Intent) {
        when (intent) {

            is ConstructorContract.Intent.ChangingPlaceholder -> {
                reduce { it.copy(placeHolder = intent.placeholder) }
            }

            is ConstructorContract.Intent.ChangingIdValue -> {
                reduce { it.copy(idValue = intent.idValue) }
            }

            is ConstructorContract.Intent.OnChangeVisibilityComponentState -> {
                reduce { it.copy(visibilityComponentState = intent.value) }
            }

            is ConstructorContract.Intent.ChangingComponentId -> {
                reduce { it.copy(componentId = intent.value) }
            }

            is ConstructorContract.Intent.ChangingVisibilityValue -> {
                reduce { it.copy(visibilityValue = intent.value) }
            }

            is ConstructorContract.Intent.ChangingOperator -> {
                reduce { it.copy(operator = intent.value) }
            }

            is ConstructorContract.Intent.EnteringName -> {
                name = intent.name
                reduce { it.copy(name = intent.name) }
            }

            is ConstructorContract.Intent.ChangingSelectedComponent -> {
                reduce { it.copy(selectedComponent = intent.component) }
            }

            is ConstructorContract.Intent.ChangingSelectedInputType -> {
                reduce { it.copy(selectedInputType = intent.type) }
            }

            is ConstructorContract.Intent.ChangingTextValue -> {
                reduce { it.copy(textValue = intent.value) }
            }

            is ConstructorContract.Intent.SetSelectedDate -> {
                reduce { it.copy(selectedDate = intent.date) }
            }

            is ConstructorContract.Intent.AddItemToSelector -> {
                val list = uiState.value.selectorItems.toMutableList()

                list.add(intent.text)

                reduce { it.copy(selectorItems = list) }
            }

            is ConstructorContract.Intent.AddItemToMultiSelector -> {
                val list = uiState.value.multiSelectorItems.toMutableList()

                list.add(intent.text)

                reduce { it.copy(multiSelectorItems = list) }
            }

            ConstructorContract.Intent.ClickVisibilityState -> {
                reduce { it.copy(visibilityState = !uiState.value.visibilityState) }
            }

            ConstructorContract.Intent.ClickCheckBoxID -> {
                reduce { it.copy(idCheckState = !uiState.value.idCheckState) }
            }

            is ConstructorContract.Intent.ChangeSelectorAnswer -> {
                reduce { it.copy(selecterAnswer = intent.text) }
            }

            is ConstructorContract.Intent.ChangeMultiSelectorAnswer -> {
                reduce { it.copy(multiSelectorAnswer = intent.text) }
            }

            ConstructorContract.Intent.ClickAddButtonVisibility -> {
                uiState.value.apply {
                    list.add(
                        VisibilityModule(
                            componentId,
                            visibilityComponentState,
                            operator,
                            visibilityValue
                        )
                    )
                }

                reduce {
                    it.copy(
                        componentId = "",
                        visibilityComponentState = "select",
                        operator = "",
                        visibilityValue = ""
                    )
                }

            }

            is ConstructorContract.Intent.ClickCheckBoxIsEnabledMaxLength -> {
                reduce { it.copy(isEnableMaxLength = intent.isEnabled) }
            }

            is ConstructorContract.Intent.ChangingMaxLength -> {
                reduce { it.copy(maxLength = intent.maxLength) }
            }

            ConstructorContract.Intent.ClickCreateButton -> {

                if (!uiState.value.visibilityState) {
                    viewModelScope.launch {
                        uiState.value.apply {
                            repository.addComponent(
                                name, ComponentsModel(
                                    componentsName = selectedComponent,
                                    input = selectedComponent,
                                    placeHolder = placeHolder,
                                    type = selectedInputType,
                                    text = textValue,
                                    id = idValue,
                                    maxLength = maxLength,
                                    isEnableMaxLength = isEnableMaxLength,
                                    color = 0xFF0F1C2,
                                    selectorDataQuestion = selecterAnswer,
                                    selectorDataAnswers = selectorItems,
                                    idVisibility = componentId,
                                    visibility = false,
                                    operator = operator,
                                    value = visibilityValue,
                                    datePicker = selectedDate,
                                    multiSelectDataQuestion = multiSelectorAnswer,
                                    multiSelectorDataAnswers = multiSelectorItems
                                )
                            )
                        }
                        list = ArrayList()

                        uiState.update {
                            it.copy(
                                componentList = listOf(
                                    "Input",
                                    "Text",
                                    "Selector",
                                    "MultiSelector",
                                    "Date Picker"
                                ),
                                inputTypeList = listOf(
                                    "Text",
                                    "Number",
                                    "Email",
                                    "Phone"
                                ),
                                selectorItems = listOf(),

                                multiSelectorItems = listOf(),

                                selectedComponent = uiState.value.componentList[0],

                                selectedInputType = uiState.value.inputTypeList[0],

                                placeHolder = "",

                                textValue = "",

                                name = "",

                                idCheckState = false,

                                idValue = "",

                                visibilityState = false,

                                componentId = "",

                                operator = "",

                                isEnableMaxLength = false,

                                maxLength = 0,

                                visibilityValue = "",

                                selectedDate = "",

                                selecterAnswer = "",

                                multiSelectorAnswer = "",

                                visibilityCheck = true
                            )
                        }

                        reduce {
                            it.copy(
                                placeHolder = "",
                                selectedInputType = uiState.value.inputTypeList[0]
                            )
                        }
                        direction.back()
                    }
                } else if (uiState.value.visibilityState) {
                    viewModelScope.launch {
                        uiState.value.apply {
                            repository.addComponent(
                                name, ComponentsModel(
                                    componentsName = selectedComponent,
                                    input = selectedComponent,
                                    placeHolder = placeHolder,
                                    type = selectedInputType,
                                    text = textValue,
                                    id = idValue,
                                    color = 0xFF0F1C2,
                                    selectorDataQuestion = selecterAnswer,
                                    selectorDataAnswers = selectorItems,
                                    idVisibility = componentId,
                                    visibility = true,
                                    isEnableMaxLength = false,
                                    maxLength = 0,
                                    operator = operator,
                                    value = visibilityValue,
                                    datePicker = selectedDate,
                                    list = Gson().toJson(list) ,
                                    multiSelectDataQuestion = multiSelectorAnswer,
                                    multiSelectorDataAnswers = multiSelectorItems
                                )
                            )
                        }
                        list = ArrayList()
                        uiState.update {
                            it.copy(
                                componentList = listOf(
                                    "Input",
                                    "Text",
                                    "Selector",
                                    "MultiSelector",
                                    "Date Picker"
                                ),
                                inputTypeList = listOf(
                                    "Text",
                                    "Number",
                                    "Email",
                                    "Phone"
                                ),
                                selectorItems = listOf(),
                                multiSelectorItems = listOf(),
                                selectedComponent = uiState.value.componentList[0],
                                selectedInputType = uiState.value.inputTypeList[0],
                                placeHolder = "",
                                textValue = "",
                                name = "",
                                idCheckState = false,
                                idValue = "",
                                visibilityState = false,
                                componentId = "",
                                operator = "",
                                isEnableMaxLength = false,
                                maxLength = 0,
                                visibilityValue = "",
                                selectedDate = "",
                                selecterAnswer = "",
                                multiSelectorAnswer = ""
                            )
                        }

                        reduce {
                            it.copy(
                                placeHolder = "",
                                selectedInputType = uiState.value.inputTypeList[0]
                            )
                        }

                        direction.back()
                    }
                }
            }
        }
    }
}