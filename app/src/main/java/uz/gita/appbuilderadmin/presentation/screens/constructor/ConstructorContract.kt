package uz.gita.appbuilderadmin.presentation.screens.constructor

import kotlinx.coroutines.flow.StateFlow

interface ConstructorContract {

    interface ViewModel {

        val uiState : StateFlow<UiState>

        fun onEventDispatchers(intent : Intent)

    }


    data class UiState (
        val componentList : List<String> = listOf(
            "component",
            "Input" ,
            "Text" ,
            "Selector" ,
            "MultiSelector" ,
            "Date Picker"
        ) ,
        val inputTypeList : List<String> = listOf(
            "Type",
            "Number",
            "Email" ,
            "Text"
        ) ,
        val selectedComponent : String = componentList[0] ,
        val selectedInputType : String = inputTypeList[0] ,
        val placeHolder : String = "" ,
        val textValue : String = "" ,
        val name : String = ""
    )

    interface Intent {
        data class ChangingSelectedComponent(
            val component : String
        ) : Intent

        data class EnteringName(
            val name : String ,
        ) : Intent

        object ClickCreateButton : Intent

        data class ChangingSelectedInputType(
            val type : String
        ) : Intent

        data class ChangingPlaceholder(
            val placeholder : String
        ) : Intent

        data class ChangingTextValue(
            val value : String
        ) : Intent

    }
}