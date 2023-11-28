package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.data.model.ComponentsModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleSpinner(
    question: String = "",
    data: ComponentsModel,
    onClick: () -> Unit = {},
) {

    var selected by remember { mutableStateOf(data.preselected) }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {


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
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onClick() }
                        .align(CenterVertically)
                        .padding(end = 10.dp, top = 2.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }

    }
}

