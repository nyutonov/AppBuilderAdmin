package uz.gita.appbuilderadmin.presentation.screens.constructor

import kotlinx.coroutines.flow.StateFlow

interface ConstructorContract {

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)

    }


    data class UiState(
        val componentList: List<String> = listOf(
            "Input",
            "Text",
            "Selector",
            "MultiSelector",
            "Date Picker"
        ),
        val inputTypeList: List<String> = listOf(
            "Text",
            "Number",
            "Email",
            "Phone"
        ),
        val selectorItems: List<String> = listOf("empty"),
        val multiSelectorItems: List<String> = listOf("empty"),
        val selectedComponent: String = componentList[0],
        val selectedInputType: String = inputTypeList[0],
        val placeHolder: String = "",
        val textValue: String = "",
        val name: String = "",
        val idCheckState: Boolean = false,
        val idValue: String = "",
        val visibilityState: Boolean = false,
        val componentId : String = "",
        val operator : String = "",
        val visibilityValue : String = "",
        val selectedDate: String = "",
        val selecterAnswer: String = "",
        val multiSelectorAnswer: String = ""
    )
    interface Intent {
        data class ChangingSelectedComponent(
            val component: String
        ) : Intent

        data class ChangingVisibilityValue (
            val value : String
        ) : Intent

        data class ChangingOperator(
            val value : String
        ) : Intent

        data class ChangingIdValue(
            val idValue: String
        ) : Intent

        data class ChangingComponentId(
            val value : String
        ) : Intent

        data class EnteringName(
            val name: String,
        ) : Intent

        object ClickCreateButton : Intent

        object ClickVisibilityState : Intent

        object ClickCheckBoxID : Intent

        data class ChangingSelectedInputType(
            val type: String
        ) : Intent

        data class ChangingPlaceholder(
            val placeholder: String
        ) : Intent

        data class ChangingTextValue(
            val value: String
        ) : Intent

        data class SetSelectedDate(
            val date: String
        ) : Intent

        data class AddItemToSelector(
            val text: String
        ) : Intent

        data class AddItemToMultiSelector(
            val text: String
        ) : Intent

        data class ChangeSelectorAnswer(
            val text: String
        ) : Intent

        data class ChangeMultiSelectorAnswer(
            val text: String
        ) : Intent
    }
}