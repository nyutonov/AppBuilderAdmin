package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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

    val regexSign = "[^<>|=]".toRegex()

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
    if(uiState.visibilityState) {
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
        MyTextField(
            value = uiState.operator,
            listener = {
                onEventDispatchers(
                    ConstructorContract.Intent.ChangingOperator(
                        it
                            .replace(regexSign , "")
                    )
                )
            }
        )
        MyText(value = "Value")
        MyTextField(
            value = uiState.visibilityValue,
            listener = {
                onEventDispatchers(
                    ConstructorContract.Intent.ChangingVisibilityValue(it)
                )
            }
        )
    }

}