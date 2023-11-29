package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.SelectorModule
import uz.gita.appbuilderadmin.data.model.VisibilityModule
import uz.gita.appbuilderadmin.data.model.VisibilityTypeModule
import uz.gita.appbuilderadmin.domain.repository.Repository
import uz.gita.appbuilderadmin.presentation.components.RowComponent
import uz.gita.appbuilderadmin.utils.extensions.myToast
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

            ConstructorContract.Intent.ClickVisibilityAddButton -> {
                reduce { it.copy(addButtonVisibilityState = true) }
            }

            ConstructorContract.Intent.LoadData -> {
                reduce { it.copy(listAllInputId = repository.getAllListInputId()) }
                reduce { it.copy(listAllSelectorId = repository.getAllSelectorId()) }
                reduce { it.copy(listAllMultiSelectorId = repository.getAllMultiSelectorId()) }
            }

            is ConstructorContract.Intent.ChangeSelectedMultiSelectorId -> {
                Log.d("TTT", "id :${intent.value}")
                reduce {
                    it.copy(
                        selectedMultiSelectorList = repository.getMultiSelectorValueListById(
                            intent.value
                        )
                    )
                }
                reduce { it.copy(selectedMultiSelectorId = intent.value) }
                reduce { it.copy(componentId = intent.value) }
            }

            is ConstructorContract.Intent.ChangeSelectedSelectorId -> {
                reduce { it.copy(selectedSelectorList = repository.getSelectorValueListById(intent.value)) }
                reduce { it.copy(selectedSelectorId = intent.value) }
                reduce { it.copy(componentId = intent.value) }
            }

            is ConstructorContract.Intent.ChangeSelectedInputId -> {
                reduce { it.copy(selectedInputId = intent.value) }
            }

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

            is ConstructorContract.Intent.ChangingSelectedComponentInRow -> {
                reduce { it.copy(selectedComponentInRow = intent.componentInRow) }

            }

            is ConstructorContract.Intent.ChangingSelectedInputType -> {
                reduce {
                    it.copy(
                        selectedInputType = intent.type,
                        isMinValueForNumberEnabled = false,
                        isMaxValueForNumberEnabled = false,
                        isMinLengthForTextEnabled = false,
                        isMaxLengthForTextEnabled = false
                    )
                }
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

            is ConstructorContract.Intent.ChangeIsRequired -> {
                reduce { it.copy(isRequired = intent.isRequired) }
            }

            is ConstructorContract.Intent.ChangeImageInputType -> {
                if (uiState.value.selectedImageInputType != intent.value) {
                    reduce { it.copy(selectedImageInputType = intent.value, selectedImageUri = "", isExist = false) }
                }
            }

            is ConstructorContract.Intent.ChangeIsMaxLengthForTextEnabled -> {
                reduce { it.copy(isMaxLengthForTextEnabled = intent.value) }
            }

            is ConstructorContract.Intent.ChangeImageUri -> {
                reduce { it.copy(selectedImageUri = intent.imageUri) }
            }

            is ConstructorContract.Intent.ChangeMaxLengthForText -> {
                reduce { it.copy(maxLengthForText = intent.value) }
            }

            is ConstructorContract.Intent.ChangeIsMinLengthForTextEnabled -> {
                reduce { it.copy(isMinLengthForTextEnabled = intent.value) }
            }

            is ConstructorContract.Intent.ChangeMinLengthForText -> {
                reduce { it.copy(minLengthForText = intent.value) }
            }

            is ConstructorContract.Intent.ChangeIsMaxValueForNumberEnabled -> {
                reduce { it.copy(isMaxValueForNumberEnabled = intent.value) }
            }

            is ConstructorContract.Intent.ChangeMaxValueForNumber -> {
                reduce { it.copy(maxValueForNumber = intent.value) }
            }

            is ConstructorContract.Intent.ChangeIsMinValueForNumberEnabled -> {
                reduce { it.copy(isMinValueForNumberEnabled = intent.value) }
            }

            is ConstructorContract.Intent.ChangeMinValueForNumber -> {
                reduce { it.copy(minValueForNumber = intent.value) }
            }

            ConstructorContract.Intent.ClickAddButtonVisibility -> {
                myToast("Visibility is added")
                reduce { it.copy(firstClickState = false, addButtonVisibilityState = false) }
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
                reduce { it.copy(listVisibilitiesValue = list) }
                removeAllData()
            }

            is ConstructorContract.Intent.ClickCreateRowComponent -> {

                uiState.update {
                    val list = uiState.value.rowType


                    Log.d("TTT", "onEventDispatchers:${uiState.value.selectedComponentInRow} ")

                    uiState.value.apply {
                        list.add(
                            ComponentsModel(
                                componentsName = selectedComponentInRow,
                                input = selectedComponent,
                                placeHolder = placeHolder,
                                type = selectedInputType,
                                text = textValue,
                                id = idValue,
                                color = 0xFF0F1C2,
                                selectorDataQuestion = selecterAnswer,
                                selectorDataAnswers = selectorItems,
                                idVisibility = componentId,
                                datePicker = selectedDate,
                                multiSelectDataQuestion = multiSelectorAnswer,
                                multiSelectorDataAnswers = multiSelectorItems,
                                weight = intent.weight
                            )
                        )
                    }
                    it.copy(rowType = list, isChanged = !uiState.value.isChanged)

                }

                Log.d("TTT", "onEventDispatchers: ${uiState.value.rowType.size}")
                Log.d("TTT", "onEventDispatchers: ${uiState.value.rowType}")
                removeAllData()
                removeUiStateForRow()
            }


            ConstructorContract.Intent.ClickCreateButton -> {
                if (uiState.value.visibilityState && uiState.value.firstClickState) {
                    myToast("Firstly you need to add visibility")
                } else {
                    if (uiState.value.selectedInputType == "Image") {
                        repository.uploadImage(uiState.value.selectedImageUri.toUri())
                            .onEach {  }
                            .launchIn(viewModelScope)
                    }

                    removeAllData()
                    uiState.value.apply {
                        if (idValue.isNotEmpty() && selectedComponent == "Input") {
                            repository.addVisibilityModule(
                                VisibilityTypeModule(
                                    idValue,
                                    "Input",
                                    listOf()
                                )
                            )
                        } else if (idValue.isNotEmpty() && selectedComponent == "Selector") {
                            repository.addVisibilityModule(
                                VisibilityTypeModule(
                                    idValue,
                                    selectedComponent,
                                    selectorItems
                                )
                            )
                        } else if (idValue.isNotEmpty() && selectedComponent == "MultiSelector") {
                            repository.addVisibilityModule(
                                VisibilityTypeModule(
                                    idValue,
                                    selectedComponent,
                                    multiSelectorItems
                                )
                            )
                        }
                    }
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
                                        isMaxLengthForTextEnabled = isMaxLengthForTextEnabled,
                                        maxLengthForText = maxLengthForText,
                                        isMinLengthForTextEnabled = isMinLengthForTextEnabled,
                                        minLengthForText = minLengthForText,
                                        isMaxValueForNumberEnabled = isMaxValueForNumberEnabled,
                                        maxValueForNumber = maxValueForNumber,
                                        isMinValueForNumberEnabled = isMinValueForNumberEnabled,
                                        minValueForNumber = minValueForNumber,
                                        isRequired = isRequired,
                                        color = 0xFF0F1C2,
                                        selectorDataQuestion = selecterAnswer,
                                        selectorDataAnswers = selectorItems,
                                        idVisibility = componentId,
                                        visibility = false,
                                        operator = operator,
                                        value = visibilityValue,
                                        datePicker = selectedDate,
                                        multiSelectDataQuestion = multiSelectorAnswer,
                                        multiSelectorDataAnswers = multiSelectorItems,
                                        rowType = Gson().toJson(rowType)
                                    )
                                )
                            }
                            list = ArrayList()

                            removeUiState()

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
                                        isMaxLengthForTextEnabled = isMaxLengthForTextEnabled,
                                        maxLengthForText = maxLengthForText,
                                        isMinLengthForTextEnabled = isMinLengthForTextEnabled,
                                        minLengthForText = minLengthForText,
                                        isMaxValueForNumberEnabled = isMaxValueForNumberEnabled,
                                        maxValueForNumber = maxValueForNumber,
                                        isMinValueForNumberEnabled = isMinValueForNumberEnabled,
                                        minValueForNumber = minValueForNumber,
                                        isRequired = isRequired,
                                        operator = operator,
                                        value = visibilityValue,
                                        datePicker = selectedDate,
                                        list = Gson().toJson(list),
                                        multiSelectDataQuestion = multiSelectorAnswer,
                                        multiSelectorDataAnswers = multiSelectorItems
                                    )
                                )
                            }
                            list = ArrayList()
                            removeUiState()

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

    private fun removeAllData() {

        reduce {
            it.copy(

                visibilityComponentState = "",
                enteringSelectorsList = listOf(),
                selectorVisibilityIdCheck = false,
                selectedVisibilityList = listOf(),
                selectedImageInputType = "Select",
                selectedImageColor = Color.Transparent.toArgb(),
                selectedImageUri = "",
                listAllInputId = listOf(),
                listAllSelectorId = listOf(),
                listAllMultiSelectorId = listOf(),
                selectedInputId = "",
                selectedSelectorId = "",
                selectedMultiSelectorId = "",
                selectedSelectorList = listOf(),
                selectedMultiSelectorList = listOf(),
                addButtonVisibilityState = false,
                operator = "",
                visibilityValue = ""
            )
        }
    }

    private fun removeUiState() {
        reduce {
            it.copy(
                rowType = mutableListOf(),
                componentList = listOf(
                    "Input",
                    "Text",
                    "Selector",
                    "MultiSelector",
                    "Date Picker",
                    "Row"
                ),
                inputTypeList = listOf(
                    "Text",
                    "Number",
                    "Email",
                    "Phone"
                ),
                selectedImageInputType = "Select",
                selectedImageColor = Color.Transparent.toArgb(),
                selectedImageUri = "",
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
                visibilityValue = "",
                selectedDate = "",
                selecterAnswer = "",
                isMaxLengthForTextEnabled = false,
                maxLengthForText = 0,
                isMinLengthForTextEnabled = false,
                minLengthForText = 0,
                isMaxValueForNumberEnabled = false,
                maxValueForNumber = 0,
                isMinValueForNumberEnabled = false,
                minValueForNumber = 0,
                isRequired = false,
                multiSelectorAnswer = "",
                visibilityCheck = true,
                visibilityComponentState = "",
                enteringSelectorsList = listOf(),
                selectorVisibilityIdCheck = false,
                selectedVisibilityList = listOf(),
                listAllInputId = listOf(),
                listAllSelectorId = listOf(),
                listAllMultiSelectorId = listOf(),
                selectedInputId = "",
                selectedSelectorId = "",
                selectedMultiSelectorId = "",
                selectedSelectorList = listOf(),
                selectedMultiSelectorList = listOf(),
                addButtonVisibilityState = false,
                listVisibilitiesValue = listOf()
            )
        }
    }
    private fun removeUiStateForRow() {
        reduce {
            it.copy(
//                rowType = mutableListOf(),
                componentList = listOf(
                    "Input",
                    "Text",
                    "Selector",
                    "MultiSelector",
                    "Date Picker",
                    "Row"
                ),
                inputTypeList = listOf(
                    "Text",
                    "Number",
                    "Email",
                    "Phone"
                ),
                selectedImageInputType = "Select",
                selectedImageColor = Color.Transparent.toArgb(),
                selectedImageUri = "",
                selectorItems = listOf(),
                multiSelectorItems = listOf(),
                selectedComponent = "Row",
                selectedInputType = "Row",
                placeHolder = "",
                textValue = "",
                name = "",
                idCheckState = false,
                idValue = "",
                visibilityState = false,
                componentId = "",
                operator = "",
                visibilityValue = "",
                selectedDate = "",
                selecterAnswer = "",
                isMaxLengthForTextEnabled = false,
                maxLengthForText = 0,
                isMinLengthForTextEnabled = false,
                minLengthForText = 0,
                isMaxValueForNumberEnabled = false,
                maxValueForNumber = 0,
                isMinValueForNumberEnabled = false,
                minValueForNumber = 0,
                isRequired = false,
                multiSelectorAnswer = "",
                visibilityCheck = true,
                visibilityComponentState = "",
                enteringSelectorsList = listOf(),
                selectorVisibilityIdCheck = false,
                selectedVisibilityList = listOf(),
                listAllInputId = listOf(),
                listAllSelectorId = listOf(),
                listAllMultiSelectorId = listOf(),
                selectedInputId = "",
                selectedSelectorId = "",
                selectedMultiSelectorId = "",
                selectedSelectorList = listOf(),
                selectedMultiSelectorList = listOf(),
                addButtonVisibilityState = false,
                listVisibilitiesValue = listOf()
            )
        }
    }
}