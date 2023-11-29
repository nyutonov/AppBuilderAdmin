package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.R

@Composable
fun InputWeight(
   onChangevalue: (Float) -> Unit
) {
    var value by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = {
                value = it
                if (it.isEmpty()){
                   onChangevalue(1f)
                }
        else{
            onChangevalue(it.filter { it.isDigit() }.toFloat())
                }
            },
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent)
                .padding(20.dp),
            placeholder = {
                Text(text = "Weight")
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}

@Composable
@Preview
fun InputWeightPrev() {
    InputWeight(){}
}