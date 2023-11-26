package uz.gita.appbuilderadmin.presentation.screens.constructor

import kotlinx.coroutines.flow.StateFlow
import uz.gita.appbuilderadmin.data.model.SelectorModule

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
        val selectorItems: List<String> = listOf(),
        val multiSelectorItems: List<String> = listOf(),
        val selectedComponent: String = componentList[0],
        val selectedInputType: String = inputTypeList[0],
        val placeHolder: String = "",
        val textValue: String = "",
        val name: String = "",
        val idCheckState: Boolean = false,
        val idValue: String = "",
        val visibilityState: Boolean = false,
        val isEnableMaxLength: Boolean = false,
        val maxLength: Int = 0,
        val componentId : String = "",
        val operator : String = "",
        val visibilityValue : String = "",
        val selectedDate: String = "",
        val selecterAnswer: String = "",
        val multiSelectorAnswer: String = "" ,
        val visibilityCheck : Boolean = true ,
        val visibilityComponentState : String = "" ,
        val enteringSelectorsList : List<SelectorModule> = listOf() ,
        val selectorVisibilityIdCheck : Boolean = false ,
        val selectedVisibilityList : List<String> = listOf()
    )
    interface Intent {
        data class ChangingSelectedComponent(
            val component: String
        ) : Intent

        data class ChangingVisibilityValue (
            val value : String
        ) : Intent

        data class OnChangeVisibilityComponentState(
            val value  :String
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

        object ChangeVisibilityCheck : Intent

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

        data class ClickCheckBoxIsEnabledMaxLength(
            val isEnabled: Boolean
        ) : Intent

        data class ChangingMaxLength(
            val maxLength: Int
        ) : Intent

        object ClickAddButtonVisibility : Intent
    }
}