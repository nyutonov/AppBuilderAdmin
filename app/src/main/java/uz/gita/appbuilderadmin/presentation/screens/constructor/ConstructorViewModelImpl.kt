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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.SelectorModule
import uz.gita.appbuilderadmin.data.model.VisibilityModule
import uz.gita.appbuilderadmin.data.model.VisibilityTypeModule
import uz.gita.appbuilderadmin.domain.repository.Repository
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

            is ConstructorContract.Intent.OnChangeInList -> {
                reduce { it.copy(inList = intent.list) }
            }

            is ConstructorContract.Intent.OnChangeChoseComponent -> {
                reduce { it.copy(choseComponent = intent.value) }
            }

            is ConstructorContract.Intent.ChangeWeight -> {

                uiState.update { it.copy(weight =
                if (intent.weight==0f)1f else intent.weight

                ) }
            }

            ConstructorContract.Intent.ClickVisibilityAddButton -> {
                reduce { it.copy(addButtonVisibilityState = true) }
            }

            ConstructorContract.Intent.LoadData -> {
                reduce { it.copy(listAllInputId = repository.getAllListInputId()) }
                reduce { it.copy(listAllSelectorId = repository.getAllSelectorId()) }
                reduce { it.copy(listAllMultiSelectorId = repository.getAllMultiSelectorId()) }
            }

            is ConstructorContract.Intent.OnChangeInMultiSelectorId -> {
                reduce {
                    it.copy(
                        selectedMultiSelectorList = repository.getMultiSelectorValueListById(intent.value) ,
                        inMultiSelectorId = intent.value
                    )
                }
            }

            is ConstructorContract.Intent.OnChangeInMultiSelectorValue -> {
                reduce { it.copy(inMultiSelectorValue = intent.value) }
            }

            is ConstructorContract.Intent.ChangeSelectedMultiSelectorId -> {
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

            is ConstructorContract.Intent.ChangeIsIdInputted -> {
                reduce { it.copy(isIdInputted = intent.value) }
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
                if (intent.component == "Image") {
                    reduce { it.copy(listAllInputId = repository.getAllListInputId()) }
                }

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
                    reduce {
                        it.copy(
                            selectedImageInputType = intent.value,
                            selectedImageUri = "",
                            isExist = false
                        )
                    }
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

            is ConstructorContract.Intent.ChangeDialogShowing -> {
                reduce { it.copy(isShowingColorDialog = intent.value) }
            }

            is ConstructorContract.Intent.ChangeColorForImage -> {
                reduce { it.copy(selectedImageColor = intent.color, isShowingColorDialog = false) }
            }

            is ConstructorContract.Intent.ChangeMaxValueForNumber -> {
                reduce { it.copy(maxValueForNumber = intent.value) }
            }

            is ConstructorContract.Intent.ChangeIsMinValueForNumberEnabled -> {
                reduce { it.copy(isMinValueForNumberEnabled = intent.value) }
            }

            is ConstructorContract.Intent.ChangeIsExist -> {
                reduce { it.copy(isExist = intent.value) }
            }

            is ConstructorContract.Intent.ChangeImageHeightPx -> {
                reduce { it.copy(imageHeightPx = intent.value) }
            }

            is ConstructorContract.Intent.ChangeMinValueForNumber -> {
                reduce { it.copy(minValueForNumber = intent.value) }
            }

            is ConstructorContract.Intent.ProgresBar ->{
                reduce { it.copy(progressBar = intent.progressBar) }
            }

            is ConstructorContract.Intent.ChangeImageSize -> {
                reduce { it.copy(selectedSize = intent.value, imageHeightPx = uiState.value.constImageHeightPx, aspectRatioY = 1f, aspectRatioX = 1f) }
            }

            is ConstructorContract.Intent.ChangeAspectRatioX -> {
                reduce { it.copy(aspectRatioX = intent.value) }
            }

            is ConstructorContract.Intent.ChangeAspectRatioY -> {
                reduce { it.copy(aspectRatioY = intent.value) }
            }

            is ConstructorContract.Intent.ChangeImageId -> {
                reduce { it.copy(selectedIdForImage = intent.value) }
            }

            is ConstructorContract.Intent.Back -> {
                viewModelScope.launch {
                    removeUiState()

                    direction.back()
                }
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
                            visibilityValue ,
                            inMultiSelectorId ,
                            inMultiSelectorValue ,
                            inList
                        )
                    )
                }
                reduce { it.copy(listVisibilitiesValue = list) }
                removeAllData()
            }

            is ConstructorContract.Intent.ClickCreateRowComponent -> {
                if (uiState.value.selectedComponent == "Row" && uiState.value.selectedComponentInRow == "Image" && uiState.value.selectedImageInputType == "Local") {
                    repository.uploadImage(uiState.value.selectedImageUri.toUri())
                        .onEach {
                            uiState.value.selectedImageUri = it.toString()

                            reduce {
                                val list = uiState.value.rowType

                                uiState.value.apply {
                                    list.add(
                                        ComponentsModel(
                                            componentsName = selectedComponentInRow,
                                            input = selectedComponent,
                                            placeHolder = placeHolder,
                                            type = selectedInputType,
                                            text = textValue,
                                            imageUri = selectedImageUri,
                                            color = selectedImageColor,
                                            heightImage = imageHeightPx.toFloat(),
                                            aspectRatio = if (aspectRatioY != 0f) (aspectRatioX / aspectRatioY) else 1f,
                                            selectedImageSize = selectedSize,
                                            id = idValue,
                                            selectorDataQuestion = selecterAnswer,
                                            selectorDataAnswers = selectorItems,
                                            idVisibility = componentId,
                                            datePicker = selectedDate,
                                            multiSelectDataQuestion = multiSelectorAnswer,
                                            multiSelectorDataAnswers = multiSelectorItems,
                                            weight = uiState.value.weight
                                        )
                                    )
                                }

                                it.copy(selectedImageUri = "", rowType = list, isChanged = !uiState.value.isChanged)
                            }
                        }
                        .launchIn(viewModelScope)
                } else {
                    uiState.update {
                        val list = uiState.value.rowType

                        uiState.value.apply {
                            list.add(
                                ComponentsModel(
                                    componentsName = selectedComponentInRow,
                                    input = selectedComponent,
                                    placeHolder = placeHolder,
                                    type = selectedInputType,
                                    text = textValue,
                                    imageUri = selectedImageUri,
                                    color = selectedImageColor,
                                    heightImage = imageHeightPx.toFloat(),
                                    aspectRatio = if (aspectRatioY != 0f) (aspectRatioX / aspectRatioY) else 1f,
                                    selectedImageSize = selectedSize,
                                    id = idValue,
                                    selectorDataQuestion = selecterAnswer,
                                    selectorDataAnswers = selectorItems,
                                    idVisibility = componentId,
                                    datePicker = selectedDate,
                                    multiSelectDataQuestion = multiSelectorAnswer,
                                    multiSelectorDataAnswers = multiSelectorItems,
                                    weight = uiState.value.weight
                                )
                            )
                        }

                        it.copy(rowType = list, isChanged = !uiState.value.isChanged)
                    }
                }
            }


            ConstructorContract.Intent.ClickCreateButton -> {
                if (uiState.value.selectedComponent == "Image") {
                    if (uiState.value.selectedImageInputType == "Local") {
                        repository.uploadImage(uiState.value.selectedImageUri.toUri())
                            .onStart { reduce { it.copy(progressBar = true) } }
                            .onEach {
                                uiState.value.selectedImageUri = it.toString()

                                viewModelScope.launch {
                                    uiState.value.apply {
                                        repository.addComponent(
                                            name, ComponentsModel(
                                                componentsName = selectedComponent,
                                                id = idValue,
                                                imageUri = selectedImageUri,
                                                selectedImageSize = selectedSize,
                                                aspectRatio = if (aspectRatioX == 0f && aspectRatioY == 0f) 0f else aspectRatioX / aspectRatioY,
                                                heightImage = imageHeightPx.toFloat(),
                                                color = selectedImageColor,
                                                idVisibility = componentId,
                                            )
                                        )
                                            .onEach { reduce { it.copy(progressBar = false) } }
                                            .launchIn(viewModelScope)
                                    }

                                    removeUiState()

                                    direction.back()
                                }
                            }.launchIn(viewModelScope)

                    } else if (uiState.value.selectedImageInputType == "Remote") {
                        viewModelScope.launch {
                            uiState.value.apply {
                                repository.addComponent(
                                    name, ComponentsModel(
                                        componentsName = selectedComponent,
                                        id = idValue,
                                        heightImage = imageHeightPx.toFloat(),
                                        selectedImageSize = selectedSize,
                                        selectedIdForImage = selectedIdForImage,
                                        isIdInputted = isIdInputted,
                                        aspectRatio = if (aspectRatioX == 0f && aspectRatioY == 0f) 0f else aspectRatioX / aspectRatioY,
                                        imageUri = selectedImageUri,
                                        color = selectedImageColor,
                                        idVisibility = componentId,
                                    )
                                )
                                    .onEach {  }
                                    .launchIn(viewModelScope)
                            }

                            removeUiState()

                            direction.back()
                        }
                    }
                } else if (uiState.value.visibilityState && uiState.value.firstClickState) {
                    myToast("Firstly you need to add visibility")
                } else {
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
                                        imageUri = selectedImageUri,
                                        isMaxLengthForTextEnabled = isMaxLengthForTextEnabled,
                                        maxLengthForText = maxLengthForText,
                                        isMinLengthForTextEnabled = isMinLengthForTextEnabled,
                                        minLengthForText = minLengthForText,
                                        isMaxValueForNumberEnabled = isMaxValueForNumberEnabled,
                                        maxValueForNumber = maxValueForNumber,
                                        isMinValueForNumberEnabled = isMinValueForNumberEnabled,
                                        minValueForNumber = minValueForNumber,
                                        isRequired = isRequired,
                                        color = selectedImageColor,
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
                                    .onEach {  }
                                    .launchIn(viewModelScope)
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
                                        imageUri = selectedImageUri,
                                        color = selectedImageColor,
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
                                        list = list,
                                        multiSelectDataQuestion = multiSelectorAnswer,
                                        multiSelectorDataAnswers = multiSelectorItems
                                    )
                                )
                                    .onEach {  }
                                    .launchIn(viewModelScope)
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
                placeHolder = "",
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
                    "Row",
                    "Image",
                ),
                inputTypeList = listOf(
                    "Text",
                    "Number",
                    "Email",
                    "Phone"
                ),
                imageHeightPx = "",
                constImageHeightPx = "",
                selectedSize = "Auto",
                aspectRatioX = 1f,
                aspectRatioY = 1f,
                isShowingColorDialog = false,
                isExist = false,
                selectedImageInputType = "Select",
                selectedImageColor = 0U,
                selectedImageUri = "",
                selectorItems = listOf(),
                selectedIdForImage = "Select Id",
                isIdInputted = false,
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
                imageHeightPx = "",
                constImageHeightPx = "",
                selectedSize = "Auto",
                aspectRatioX = 1f,
                aspectRatioY = 1f,
                isShowingColorDialog = false,
                isExist = false,
                selectedImageInputType = "Select",
                selectedImageColor = 0U,
                selectedImageUri = "",
                selectedIdForImage = "Select Id",
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