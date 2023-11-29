package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.data.model.ComponentsModel

@Composable
fun RowInputComponent(
    data: ComponentsModel,
    onClick: () -> Unit = {},
) {
    var value by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier
                .weight(
                    data.weight
                )
        ) {
            TextField(
                value = value,
                onValueChange = { input ->
                    if (data.type == "Number") {
                        val numericValue = input.filter { it.isDigit() }

                        if (data.isMaxValueForNumberEnabled) {
                            if (numericValue.isEmpty()) {
                                value = ""
                            } else {
                                numericValue.toIntOrNull()?.let { number ->
                                    if (numericValue[0] != '0' && number < data.maxValueForNumber) {
                                        value = numericValue
                                    }
                                }
                            }
                        } else {
                            value = numericValue
                        }
                    } else if (data.type == "Text") {
                        if (data.isMaxLengthForTextEnabled) {
                            if (input.length <= data.maxLengthForText) {
                                value = input
                            }
                        } else {
                            value = input
                        }
                    } else {
                        value = input
                    }
                },

                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .padding(20.dp),
                placeholder = {
                    Text(text = data.placeHolder)
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = when (data.type) {
                        "Email" -> KeyboardType.Email
                        "Number" -> KeyboardType.Number
                        "Phone" -> KeyboardType.Phone
                        else -> KeyboardType.Text
                    }
                )
            )
        }
    }
}


@Composable
@Preview
fun Prev() {
    RowInputComponent(data = ComponentsModel(), {})
}

