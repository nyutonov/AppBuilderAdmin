package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.presentation.components.DateComponent
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.MultiSelectorComponent
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.presentation.screens.register.RegisterContract
import uz.mlsoft.mydemosforquiz.ui.components.DemoSpinner

class ConstructorScreen(
    private val name: String
) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: ConstructorContract.ViewModel = getViewModel<ConstructorViewModelImpl>()
        viewModel.onEventDispatchers(ConstructorContract.Intent.EnteringName(name))
        ConstructorScreenContent(
            viewModel.uiState.collectAsState().value,
            viewModel::onEventDispatchers
        )
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun ConstructorScreenContent(
    uiState: ConstructorContract.UiState,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F1C2E)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFF0F1C2E))
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "CONSTRUCTOR",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    )
                )
            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, Color.LightGray)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = "Preview",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                        fontWeight = FontWeight.W400,
                        color = Color.LightGray
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))

                when (uiState.selectedComponent) {

                    "Input" -> {
                        InputComponent(
                            data = ComponentsModel(
                                type = uiState.selectedInputType,
                                placeHolder = uiState.placeHolder
                            )
                        )
                    }

                    "Text" -> {
                        TextComponent(
                            data = ComponentsModel(
                                text = uiState.textValue
                            )
                        )
                    }

                    "Selector" -> {
                        val list = listOf(
                            "empty1",
                            "empty2",
                            "empty3",
                            "empty4"
                        )

                        DemoSpinner(
                            list = list,
                            preselected = list[0],
                            onSelectionChanged = {},
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                        )
                    }

                    "MultiSelector" -> {
                        val list = listOf(
                            "empty1",
                            "empty2",
                            "empty3",
                            "empty4"
                        )

                        MultiSelectorComponent(list = list)
                    }

                    "Date Picker" -> {
                        DateComponent()
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 70.dp)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(start = 10.dp),
                            text = "Components",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                fontWeight = FontWeight.W400,
                                color = Color.White
                            )
                        )
                        DemoSpinner(
                            list = uiState.componentList,
                            preselected = uiState.selectedComponent,
                            onSelectionChanged = {
                                Log.d("TTT", it)

                                onEventDispatchers(
                                    ConstructorContract.Intent.ChangingSelectedComponent(
                                        it
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        )

                        if (uiState.selectedComponent == "Input") {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Set Type",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            DemoSpinner(
                                list = uiState.inputTypeList,
                                preselected = uiState.selectedInputType,
                                onSelectionChanged = {
                                    onEventDispatchers(
                                        ConstructorContract.Intent.ChangingSelectedInputType(it)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Set Place Holder",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(58.dp)
                                    .padding(horizontal = 15.dp),
                                value = uiState.placeHolder,
                                singleLine = true,
                                onValueChange = {
                                    onEventDispatchers(
                                        ConstructorContract.Intent.ChangingPlaceholder(
                                            it
                                        )
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.LightGray,
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedTextColor = Color.LightGray,
                                    unfocusedTextColor = Color.LightGray
                                ),
                                shape = RoundedCornerShape(5.dp)
                            )

                            Spacer(modifier = Modifier.size(10.dp))
//
//                            Row (
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 15.dp) ,
//                                verticalAlignment = Alignment.CenterVertically
//                            ){
//                                Checkbox(
//                                    checked = uiState.visibilityState,
//                                    onCheckedChange = {
//                                        onEventDispatchers(ConstructorContract.Intent.ClickVisibilityState)
//                                    } ,
//                                    colors = CheckboxDefaults.colors(
//                                        checkedColor = Color(0xff4d648d)
//                                    )
//                                )
//                                Spacer(modifier = Modifier.size(5.dp))
//                                Text(
//                                    text = "Set Visibility",
//                                    style = TextStyle(
//                                        fontSize = 16.sp,
//                                        lineHeight = 24.sp,
//                                        fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
//                                        fontWeight = FontWeight.W400,
//                                        color = if (uiState.visibilityState) Color.White else Color.Gray
//                                    )
//                                )
//
//                                Spacer(modifier = Modifier.size(10.dp))
//                                Text(
//                                    text = "",
//                                    style = TextStyle(
//                                        fontSize = 16.sp,
//                                        lineHeight = 24.sp,
//                                        fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
//                                        fontWeight = FontWeight.W400,
//                                        color = if (uiState.visibilityState) Color.White else Color.Gray
//                                    )
//                                )
//                            }

                        } else if (uiState.selectedComponent == "Text") {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Text Value",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .width(310.dp)
                                    .height(58.dp),
                                value = uiState.textValue,

                                onValueChange = {
                                    onEventDispatchers(
                                        ConstructorContract.Intent.ChangingTextValue(
                                            it
                                        )
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.LightGray,
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedTextColor = Color.LightGray,
                                    unfocusedTextColor = Color.LightGray
                                ),
                                shape = RoundedCornerShape(5.dp)
                            )
                        } else if (uiState.selectedComponent == "Selector") {

                        }
                    }

                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                onEventDispatchers(ConstructorContract.Intent.ClickCreateButton)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff4d648d)
            )
        ) {
            Text(
                text = "Create",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun SetId(
    uiState: ConstructorContract.UiState,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit
) {
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        text = "Set ID",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
            fontWeight = FontWeight.W400,
            color = Color.White
        )
    )
    Spacer(modifier = Modifier.size(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(5.dp))
        Checkbox(
            checked = uiState.idCheckState,
            onCheckedChange = {
                onEventDispatchers(ConstructorContract.Intent.ClickCheckBoxID)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xff4d648d)
            )
        )
        Spacer(modifier = Modifier.size(5.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            value = uiState.idValue,

            onValueChange = {
                onEventDispatchers(ConstructorContract.Intent.ChangingIdValue(it))
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray
            ),
            shape = RoundedCornerShape(5.dp),
            enabled = uiState.idCheckState
        )
        Spacer(modifier = Modifier.size(5.dp))
    }
}

