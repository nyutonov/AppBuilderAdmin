package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.app.App
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.presentation.components.ComponentsInText
import uz.gita.appbuilderadmin.presentation.components.DateComponent
import uz.gita.appbuilderadmin.presentation.components.DemoSpinner
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.MultiSelectorComponent
import uz.gita.appbuilderadmin.presentation.components.MyText
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.presentation.components.VisibilityComponents

class ConstructorScreen(
    private val name: String,
) : AndroidScreen() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("RememberReturnType")
@Composable
fun ConstructorScreenContent(
    uiState: ConstructorContract.UiState,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit,
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
                        ) {}
                    }

                    "Text" -> {
                        TextComponent(
                            data = ComponentsModel(
                                text = uiState.textValue
                            )
                        ) {}
                    }

                    "Selector" -> {
                        Text(
                            text = uiState.selecterAnswer,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)
                        )

                        DemoSpinner(
                            list = uiState.selectorItems,
                            preselected = "Enter",
                            onSelectionChanged = {},
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {}
                    }

                    "MultiSelector" -> {
                        MultiSelectorComponent(
                            list = uiState.multiSelectorItems,
                            question = uiState.multiSelectorAnswer,
                            onClickDelete = {},
                            onLongClick = {}
                        )
                    }

                    "Date Picker" -> {
                        DateComponent(date = "", listener = {
                            onEventDispatchers(
                                ConstructorContract.Intent.SetSelectedDate(
                                    it
                                )
                            )
                        }) {}
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
                        ) {}

                        if (uiState.selectedComponent == "Input") {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)
                            RequiredComponent(onEventDispatchers)
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
                            ) {}
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

                            if (uiState.selectedInputType == "Text") {
                                Text(
                                    text = "Set Max Length",
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
                                        checked = uiState.isMaxLengthForTextEnabled,
                                        onCheckedChange = {
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeIsMaxLengthForTextEnabled(
                                                    it
                                                )
                                            )
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
                                        value = if (uiState.maxLengthForText == 0) "" else uiState.maxLengthForText.toString(),
                                        onValueChange = {
                                            val numericValue = it.filter { it.isDigit() }
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeMaxLengthForText(
                                                    if (numericValue.isEmpty()) 0 else numericValue.toInt()
                                                )
                                            )
                                        },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = Color.LightGray,
                                            unfocusedBorderColor = Color.LightGray,
                                            focusedTextColor = Color.LightGray,
                                            unfocusedTextColor = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(5.dp),
                                        enabled = uiState.isMaxLengthForTextEnabled,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        placeholder = {
                                            Text(text = "0")
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                }

                                Spacer(modifier = Modifier.size(10.dp))

                                Text(
                                    text = "Set Min Length",
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
                                        checked = uiState.isMinLengthForTextEnabled,
                                        onCheckedChange = {
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeIsMinLengthForTextEnabled(
                                                    it
                                                )
                                            )
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
                                        value = if (uiState.minLengthForText == 0) "" else uiState.minLengthForText.toString(),
                                        onValueChange = {
                                            val numericValue = it.filter { it.isDigit() }
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeMinLengthForText(
                                                    if (numericValue.isEmpty()) 0 else numericValue.toInt()
                                                )
                                            )
                                        },
                                        placeholder = {
                                            Text(text = "0")
                                        },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = Color.LightGray,
                                            unfocusedBorderColor = Color.LightGray,
                                            focusedTextColor = Color.LightGray,
                                            unfocusedTextColor = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(5.dp),
                                        enabled = uiState.isMinLengthForTextEnabled,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                }
                            } else if (uiState.selectedInputType == "Number") {
                                Text(
                                    text = "Set Max Value",
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
                                        checked = uiState.isMaxValueForNumberEnabled,
                                        onCheckedChange = {
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeIsMaxValueForNumberEnabled(
                                                    it
                                                )
                                            )
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
                                        value = if (uiState.maxValueForNumber == 0) "" else uiState.maxValueForNumber.toString(),
                                        onValueChange = {
                                            val numericValue = it.filter { it.isDigit() }
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeMaxValueForNumber(
                                                    if (numericValue.isEmpty()) 0 else numericValue.toInt()
                                                )
                                            )
                                        },
                                        placeholder = {
                                            Text(text = "0")
                                        },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = Color.LightGray,
                                            unfocusedBorderColor = Color.LightGray,
                                            focusedTextColor = Color.LightGray,
                                            unfocusedTextColor = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(5.dp),
                                        enabled = uiState.isMaxValueForNumberEnabled,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                }

                                Spacer(modifier = Modifier.size(10.dp))

                                Text(
                                    text = "Set Min Value",
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
                                        checked = uiState.isMinValueForNumberEnabled,
                                        onCheckedChange = {
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeIsMinValueForNumberEnabled(
                                                    it
                                                )
                                            )
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
                                        value = if (uiState.minValueForNumber == 0) "" else uiState.minValueForNumber.toString(),
                                        onValueChange = {
                                            val numericValue = it.filter { it.isDigit() }
                                            onEventDispatchers(
                                                ConstructorContract.Intent.ChangeMinValueForNumber(
                                                    if (numericValue.isEmpty()) 0 else numericValue.toInt()
                                                )
                                            )
                                        },
                                        placeholder = {
                                            Text(text = "0")
                                        },
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = Color.LightGray,
                                            unfocusedBorderColor = Color.LightGray,
                                            focusedTextColor = Color.LightGray,
                                            unfocusedTextColor = Color.LightGray
                                        ),
                                        shape = RoundedCornerShape(5.dp),
                                        enabled = uiState.isMinValueForNumberEnabled,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                }
                            }

                            Spacer(modifier = Modifier.size(10.dp))

                            VisibilityComponents(
                                uiState = uiState,
                                onEventDispatchers = onEventDispatchers
                            )

                        } else if (uiState.selectedComponent == "Text") {

                            ComponentsInText(uiState = uiState, onEventDispatchers = onEventDispatchers)

                        } else if (uiState.selectedComponent == "Selector") {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)

                            var text by remember { mutableStateOf("") }
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(text = "Question", color = Color.White)
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(58.dp)
                                    .padding(horizontal = 15.dp),
                                value = uiState.selecterAnswer,
                                singleLine = true,
                                onValueChange = {
                                    onEventDispatchers.invoke(
                                        ConstructorContract.Intent.ChangeSelectorAnswer(
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
                            Text(text = "Item", color = Color.White)
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(58.dp)
                                    .padding(horizontal = 15.dp),
                                value = text,
                                singleLine = true,
                                onValueChange = { text = it },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.LightGray,
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedTextColor = Color.LightGray,
                                    unfocusedTextColor = Color.LightGray
                                ),
                                shape = RoundedCornerShape(5.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                                    .width(310.dp)
                                    .height(50.dp),
                                onClick = {
                                    if (!uiState.selectorItems.contains(text) && text.isNotEmpty()) {
                                        onEventDispatchers.invoke(
                                            ConstructorContract.Intent.AddItemToSelector(
                                                text
                                            )
                                        )

                                        Toast.makeText(
                                            App.instance,
                                            "$text is added",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        text = ""
                                    } else {
                                        Toast.makeText(
                                            App.instance,
                                            "$text is not added",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff4d648d)
                                )
                            ) {
                                Text(
                                    text = "Add",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                                        fontWeight = FontWeight.W400,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.size(10.dp))
                            VisibilityComponents(
                                uiState = uiState,
                                onEventDispatchers = onEventDispatchers
                            )
                        } else if (uiState.selectedComponent == "MultiSelector") {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)

                            var text by remember { mutableStateOf("") }

                            Text(text = "Question", color = Color.White)

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(58.dp)
                                    .padding(horizontal = 15.dp),
                                value = uiState.multiSelectorAnswer,
                                singleLine = true,
                                onValueChange = {
                                    onEventDispatchers.invoke(
                                        ConstructorContract.Intent.ChangeMultiSelectorAnswer(
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

                            Text(text = "Item", color = Color.White)

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(58.dp)
                                    .padding(horizontal = 15.dp),
                                value = text,
                                singleLine = true,
                                onValueChange = { text = it },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.LightGray,
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedTextColor = Color.LightGray,
                                    unfocusedTextColor = Color.LightGray
                                ),
                                shape = RoundedCornerShape(5.dp)
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            Button(
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                                    .width(310.dp)
                                    .height(50.dp),
                                onClick = {
                                    if (!uiState.multiSelectorItems.contains(text) && text.isNotEmpty()) {
                                        onEventDispatchers.invoke(
                                            ConstructorContract.Intent.AddItemToMultiSelector(
                                                text
                                            )
                                        )
                                        Toast.makeText(
                                            App.instance,
                                            "$text is added",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        text = ""
                                    } else {
                                        Toast.makeText(
                                            App.instance,
                                            "$text is not added",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff4d648d)
                                )
                            ) {
                                Text(
                                    text = "Add",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                                        fontWeight = FontWeight.W400,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.size(10.dp))
                            VisibilityComponents(
                                uiState = uiState,
                                onEventDispatchers = onEventDispatchers
                            )
                        } else {
                            SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)
                            VisibilityComponents(
                                uiState = uiState,
                                onEventDispatchers = onEventDispatchers
                            )
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
    onEventDispatchers: (ConstructorContract.Intent) -> Unit,
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
            singleLine = true,
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

@Composable
fun RequiredComponent(
    onEventDispatchers: (ConstructorContract.Intent) -> Unit
) {
    MyText(value = "Required")
    Spacer(modifier = Modifier.size(10.dp))
    DemoSpinner(
        list = listOf(
            "true",
            "false"
        ),
        preselected = "select required",
        onSelectionChanged = {
            onEventDispatchers.invoke(ConstructorContract.Intent.ChangeIsRequired(it == "true"))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

    }
}