package uz.gita.appbuilderadmin.presentation.componentsimport androidx.compose.foundation.layout.Columnimport androidx.compose.foundation.layout.Spacerimport androidx.compose.foundation.layout.fillMaxWidthimport androidx.compose.foundation.layout.heightimport androidx.compose.foundation.layout.paddingimport androidx.compose.material3.Textimport androidx.compose.runtime.Composableimport androidx.compose.ui.Modifierimport androidx.compose.ui.unit.dpimport uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract@Composablefun ComponentsInRow(    uiState: ConstructorContract.UiState,    onEventDispatchers: (ConstructorContract.Intent) -> Unit,) {    Column(modifier = Modifier.fillMaxWidth()) {        Text(text = "Components")        Spacer(modifier = Modifier.padding(top = 5.dp))        DemoSpinner(            list = uiState.componentList,            preselected = uiState.selectedComponent,            onSelectionChanged = {                onEventDispatchers(                    ConstructorContract.Intent.ChangingSelectedComponent(                        it                    )                )            },            modifier = Modifier                .fillMaxWidth()                .height(56.dp)        ) {        }    }}