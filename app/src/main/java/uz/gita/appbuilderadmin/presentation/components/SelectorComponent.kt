package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.data.model.ComponentsModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleSpinner(
    question: String = "",
    data: ComponentsModel,
    onLongClick: () -> Unit,
) {

    var selected by remember { mutableStateOf(data.preselected) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = question,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onLongClick = onLongClick) {}
        ) {
            Column {
                OutlinedTextField(
                    value = (selected),
                    onValueChange = { },
                    label = { Text(text = "List name") },
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
                                    color = Color.White
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

