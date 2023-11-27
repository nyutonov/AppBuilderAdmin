package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract

@Composable
fun VisibilityComponents(
    uiState : ConstructorContract.UiState ,
    onEventDispatchers : (ConstructorContract.Intent) -> Unit
) {
    val regexSign = "[^<>|=]|<<|>>|<<|=<|=>".toRegex()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = uiState.visibilityState,
            onCheckedChange = {
                onEventDispatchers(ConstructorContract.Intent.ClickVisibilityState)
            } ,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xff4d648d)
            )
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "Set Visibility",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = if (uiState.visibilityState) Color.White else Color.Gray
            )
        )
    }
    if (uiState.visibilityState) {
        val list = listOf(
            "Input" ,
            "Selector" ,
            "Multi Selector"
        )
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = list,
            preselected = "select",
            onSelectionChanged = {
               onEventDispatchers(ConstructorContract.Intent.OnChangeVisibilityComponentState(it))
            },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .height(56.dp)
        ) {

        }
    }
    if(uiState.visibilityState && uiState.visibilityComponentState == "Input") {
        MyText(value = "Component ID")
        Text(
            text = "Component ID kiritilyotganda etibor berilsin agar kiritilgan id toplimasa ham visibility shartini qanoantirilmagan hisoblanadi",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = if (uiState.visibilityState) Color.White else Color.Gray ,
                textAlign = TextAlign.Center
            )
        )
        MyTextField(
            value = uiState.componentId,
            listener = {
                onEventDispatchers(ConstructorContract.Intent.ChangingComponentId(it))
            }
        )
        MyText(value = "Operator")
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "You can add only <>=",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        )
        if (uiState.operator.contains("<|>".toRegex())) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "You enter < or >. If you enter this we can check visibility only number. REMEMBER",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                    fontWeight = FontWeight.W400,
                    color = Color.White ,
                    textAlign = TextAlign.Center
                )
            )
        }
        MyTextField(
            value = uiState.operator,
            listener = {
                if (it.length < 3) {
                    onEventDispatchers(
                        ConstructorContract.Intent.ChangingOperator(
                            it
                                .replace(regexSign , "")
                        )
                    )
                    onEventDispatchers(
                        ConstructorContract.Intent.ChangeVisibilityCheck
                    )
                }
            } ,
            outlinedTextFieldColors = if (!uiState.visibilityCheck)
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedTextColor = Color.Red,
                    unfocusedTextColor = Color.Red
                )
            else OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray
            )

        )
        MyText(value = "Value")
        MyTextField(
            value = uiState.visibilityValue,
            listener = {
                onEventDispatchers(
                    ConstructorContract.Intent.ChangingVisibilityValue(it)
                )
                onEventDispatchers(
                    ConstructorContract.Intent.ChangeVisibilityCheck
                )
            },
            outlinedTextFieldColors = if (!uiState.visibilityCheck)
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedTextColor = Color.Red,
                    unfocusedTextColor = Color.Red
                )
            else OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray
            ) ,
            check = uiState.operator.contains("<|>".toRegex())
        )
    } else if (uiState.visibilityState && uiState.visibilityComponentState == "Multi Selector" || uiState.visibilityComponentState == "Selector") {

        MyText(value = "Component ID")
        Text(
            text = "Component ID kiritilyotganda etibor berilsin agar kiritilgan id toplimasa ham visibility shartini qanoantirilmagan hisoblanadi",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = if (uiState.visibilityState) Color.White else Color.Gray ,
                textAlign = TextAlign.Center
            )
        )
        MyTextField(
            value = uiState.componentId,
            listener = {
                onEventDispatchers(ConstructorContract.Intent.ChangingComponentId(it))
            }
        )

        MyText(value = "Value")
        MyTextField(
            value = uiState.visibilityValue,
            listener = {
                onEventDispatchers(ConstructorContract.Intent.ChangingVisibilityValue(it))
            }
        )


    }
    if (uiState.visibilityState) {
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp),
            onClick = {
                onEventDispatchers(ConstructorContract.Intent.ClickAddButtonVisibility)
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
        Spacer(modifier = Modifier.size(7.dp))
    }

}