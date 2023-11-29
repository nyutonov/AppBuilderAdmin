package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract

@Composable
fun ComponentsInInput(
    uiState: ConstructorContract.UiState,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit
) {

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

    /*   if (uiState.selectedInputType == "Text") {
    //        Text(
    //            text = "Set Max Length",
    //            style = TextStyle(
    //                fontSize = 16.sp,
    //                lineHeight = 24.sp,
    //                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
    //                fontWeight = FontWeight.W400,
    //                color = Color.White
    //            )
    //        )
    //
    //        Spacer(modifier = Modifier.size(10.dp))
    //
    //        Row(
    //            modifier = Modifier
    //                .fillMaxWidth()
    //                .padding(horizontal = 15.dp),
    //            verticalAlignment = Alignment.CenterVertically
    //        ) {
    //            Spacer(modifier = Modifier.size(5.dp))
    //            Checkbox(
    //                checked = uiState.isMaxLengthForTextEnabled,
    //                onCheckedChange = {
    //                    onEventDispatchers(
    //                        ConstructorContract.Intent.ChangeIsMaxLengthForTextEnabled(
    //                            it
    //                        )
    //                    )
    //                },
    //                colors = CheckboxDefaults.colors(
    //                    checkedColor = Color(0xff4d648d)
    //                )
    //            )
    //            Spacer(modifier = Modifier.size(5.dp))
    //            OutlinedTextField(
    //                modifier = Modifier
    //                    .fillMaxWidth()
    //                    .height(58.dp),
    //                value = if (uiState.maxLengthForText == 0) "" else uiState.maxLengthForText.toString(),
    //                onValueChange = {
    //                    val numericValue = it.filter { it.isDigit() }
    //                    onEventDispatchers(
    //                        ConstructorContract.Intent.ChangeMaxLengthForText(
    //                            if (numericValue.isEmpty()) 0 else numericValue.toInt()
    //                        )
    //                    )
    //                },
    //                colors = OutlinedTextFieldDefaults.colors(
    //                    focusedBorderColor = Color.LightGray,
    //                    unfocusedBorderColor = Color.LightGray,
    //                    focusedTextColor = Color.LightGray,
    //                    unfocusedTextColor = Color.LightGray
    //                ),
    //                shape = RoundedCornerShape(5.dp),
    //                enabled = uiState.isMaxLengthForTextEnabled,
    //                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    //                placeholder = {
    //                    Text(text = "0")
    //                }
    //            )
    //            Spacer(modifier = Modifier.size(5.dp))
    //        }
    //
    //        Spacer(modifier = Modifier.size(10.dp))
    //
    //        Text(
    //            text = "Set Min Length",
    //            style = TextStyle(
    //                fontSize = 16.sp,
    //                lineHeight = 24.sp,
    //                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
    //                fontWeight = FontWeight.W400,
    //                color = Color.White
    //            )
    //        )
    //
    //        Spacer(modifier = Modifier.size(10.dp))
    //
    //        Row(
    //            modifier = Modifier
    //                .fillMaxWidth()
    //                .padding(horizontal = 15.dp),
    //            verticalAlignment = Alignment.CenterVertically
    //        ) {
    //            Spacer(modifier = Modifier.size(5.dp))
    //            Checkbox(
    //                checked = uiState.isMinLengthForTextEnabled,
    //                onCheckedChange = {
    //                    onEventDispatchers(
    //                        ConstructorContract.Intent.ChangeIsMinLengthForTextEnabled(
    //                            it
    //                        )
    //                    )
    //                },
    //                colors = CheckboxDefaults.colors(
    //                    checkedColor = Color(0xff4d648d)
    //                )
    //            )
    //            Spacer(modifier = Modifier.size(5.dp))
    //            OutlinedTextField(
    //                modifier = Modifier
    //                    .fillMaxWidth()
    //                    .height(58.dp),
    //                value = if (uiState.minLengthForText == 0) "" else uiState.minLengthForText.toString(),
    //                onValueChange = {
    //                    val numericValue = it.filter { it.isDigit() }
    //                    onEventDispatchers(
    //                        ConstructorContract.Intent.ChangeMinLengthForText(
    //                            if (numericValue.isEmpty()) 0 else numericValue.toInt()
    //                        )
    //                    )
    //                },
    //                placeholder = {
    //                    Text(text = "0")
    //                },
    //                colors = OutlinedTextFieldDefaults.colors(
    //                    focusedBorderColor = Color.LightGray,
    //                    unfocusedBorderColor = Color.LightGray,
    //                    focusedTextColor = Color.LightGray,
    //                    unfocusedTextColor = Color.LightGray
    //                ),
    //                shape = RoundedCornerShape(5.dp),
    //                enabled = uiState.isMinLengthForTextEnabled,
    //                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    //            )
    //            Spacer(modifier = Modifier.size(5.dp))
    //        }
      }

     // else if (uiState.selectedInputType == "Number") {
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
        )*/
}
