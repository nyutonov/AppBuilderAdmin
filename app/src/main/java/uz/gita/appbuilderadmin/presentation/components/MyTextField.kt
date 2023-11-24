package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract

@Composable
fun MyTextField(
    value : String ,
    listener : (String) -> Unit ,
    outlinedTextFieldColors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.LightGray,
        unfocusedBorderColor = Color.LightGray,
        focusedTextColor = Color.LightGray,
        unfocusedTextColor = Color.LightGray
    ) ,
    check : Boolean = false
) {
    Spacer(modifier = Modifier.size(10.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(horizontal = 15.dp),
        value = value,
        singleLine = true,
        onValueChange = {
            listener(it)
        },
        colors = outlinedTextFieldColors,
        shape = RoundedCornerShape(5.dp) ,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (check) KeyboardType.Number else KeyboardType.Text
        )
    )
}