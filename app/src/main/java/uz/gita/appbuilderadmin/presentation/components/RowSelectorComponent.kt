package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.data.model.ComponentsModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowSelectorComponent(
    question: String = "",
    data: ComponentsModel,
    onClick: () -> Unit = {},
) {

    var selected by remember { mutableStateOf(data.preselected) }
    var expanded by remember { mutableStateOf(false) }
    Column {

        Box(
            modifier = Modifier.weight(
                if (data.weight == 0f) {
                    1f
                } else {
                    data.weight
                }
            )
        ) {

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = question,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .weight(0.7f)
//                .weight(1f)
                    ) {
                        Column {
                            OutlinedTextField(
                                value = (selected),
                                onValueChange = { },
                                placeholder = { Text(text = "Enter") },
                                modifier = Modifier.fillMaxWidth(),
                                trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                                readOnly = true,
                                colors = TextFieldDefaults.colors(Color.White)
                            )
                            DropdownMenu(
                                modifier = Modifier.fillMaxWidth(),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                data.selectorDataAnswers.forEach { entry ->

                                    DropdownMenuItem(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            selected = entry
                                            expanded = false
                                        },
                                        text = {
                                            Text(
                                                text = (entry),
                                                modifier = Modifier
                                                    .wrapContentWidth()
                                                    .align(Alignment.Start),
                                            )
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .matchParentSize()
                                .background(Color.Transparent)
                                .padding(10.dp)
                                .clickable(
                                    onClick = { expanded = !expanded }
                                )
                        )
                    }
                }
            }

        }
    }
}

