package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.core.net.toUri
import kotlinx.coroutines.flow.StateFlow
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.SelectorModule
import uz.gita.appbuilderadmin.data.model.VisibilityModule

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
            "Date Picker",
            "Row",
            "Image"
        ),
        val inputTypeList: List<String> = listOf(
            "Text",
            "Number",
            "Email",
            "Phone"
        ),

        val rowType: MutableList<ComponentsModel> = mutableListOf(),


        val imageInputTypes: List<String> = listOf(
            "Local",
            "Remote"
        ),
        val selectedImageInputType: String = "Select",
        val selectedImageColor: Int = Color.Transparent.toArgb(),
        val selectedImageUri: Uri? = null,
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
        val componentId: String = "",
        val operator: String = "",
        val visibilityValue: String = "",
        val selectedDate: String = "",
        val selecterAnswer: String = "",
        val isMaxLengthForTextEnabled: Boolean = false,
        val maxLengthForText: Int = 0,
        val isMinLengthForTextEnabled: Boolean = false,
        val minLengthForText: Int = 0,
        val isMaxValueForNumberEnabled: Boolean = false,
        val maxValueForNumber: Int = 0,
        val isMinValueForNumberEnabled: Boolean = false,
        val minValueForNumber: Int = 0,
        val isRequired: Boolean = false,
        val multiSelectorAnswer: String = "",
        val visibilityCheck: Boolean = true,
        val visibilityComponentState: String = "",
        val enteringSelectorsList: List<SelectorModule> = listOf(),
        val selectorVisibilityIdCheck: Boolean = false,
        val selectedVisibilityList: List<String> = listOf(),
        val listAllInputId: List<String> = listOf(),
        val listAllSelectorId: List<String> = listOf(),
        val listAllMultiSelectorId: List<String> = listOf(),
        val selectedInputId: String = "",
        val selectedSelectorId: String = "",
        val selectedMultiSelectorId: String = "",
        val selectedSelectorList: List<String> = listOf(),
        val selectedMultiSelectorList: List<String> = listOf(),
        val addButtonVisibilityState: Boolean = false,
        val listVisibilitiesValue: List<VisibilityModule> = listOf(),
        val firstClickState: Boolean = true
    )

    interface Intent {

        object ClickVisibilityAddButton : Intent
        object LoadData : Intent
        data class ChangeSelectedMultiSelectorId(
            val value: String
        ) : Intent

        data class ChangeSelectedSelectorId(
            val value: String
        ) : Intent

        data class ChangeSelectedInputId(
            val value: String
        ) : Intent

        data class ChangingSelectedComponent(
            val component: String
        ) : Intent

        data class ChangingVisibilityValue(
            val value: String
        ) : Intent

        data class OnChangeVisibilityComponentState(
            val value: String
        ) : Intent

        data class ChangingOperator(
            val value: String
        ) : Intent

        data class ChangingIdValue(
            val idValue: String
        ) : Intent

        data class ChangingComponentId(
            val value: String
        ) : Intent

        data class EnteringName(
            val name: String,
        ) : Intent

        object ClickCreateButton : Intent

        data class ClickCreateRowComponent(
            val weight: Float=1F
        ):Intent

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

        data class ChangeIsRequired(
            val isRequired: Boolean
        ) : Intent

        data class ChangeIsMaxLengthForTextEnabled(
            val value: Boolean
        ) : Intent

        data class ChangeMaxLengthForText(
            val value: Int
        ) : Intent

        data class ChangeIsMinLengthForTextEnabled(
            val value: Boolean
        ) : Intent

        data class ChangeMinLengthForText(
            val value: Int
        ) : Intent

        data class ChangeIsMaxValueForNumberEnabled(
            val value: Boolean
        ) : Intent

        data class ChangeMaxValueForNumber(
            val value: Int
        ) : Intent

        data class ChangeIsMinValueForNumberEnabled(
            val value: Boolean
        ) : Intent

        data class ChangeMinValueForNumber(
            val value: Int
        ) : Intent

        data class ChangeImageInputType(
            val value: String
        ) : Intent

        data class ChangeImageUri(
            val imageUri: Uri
        ) : Intent

        object ClickAddButtonVisibility : Intent
    }
}