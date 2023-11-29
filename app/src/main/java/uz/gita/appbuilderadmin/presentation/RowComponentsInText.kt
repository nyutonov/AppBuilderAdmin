package uz.gita.appbuilderadmin.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import uz.gita.appbuilderadmin.presentation.screens.constructor.SetId

@Composable
fun RowComponentsInText(
    uiState : ConstructorContract.UiState ,
    onEventDispatchers : (ConstructorContract.Intent) -> Unit
) {
//    SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)
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
        singleLine = true,
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

    Spacer(modifier = Modifier.size(10.dp))
//    VisibilityComponents(
//        uiState = uiState,
//        onEventDispatchers = onEventDispatchers
//    )

}