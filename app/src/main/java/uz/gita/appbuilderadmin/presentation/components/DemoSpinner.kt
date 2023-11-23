package uz.mlsoft.mydemosforquiz.ui.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DemoSpinner(
    list: List<String>,
    preselected: String,
    onSelectionChanged: (selection: String) -> Unit,
    modifier: Modifier
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value

    Box(modifier = modifier) {
        Column {
            OutlinedTextField(
                value = (selected),
                onValueChange = { },
                label = { Text(text = "My List") },
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                readOnly = true,
                colors = TextFieldDefaults.colors(Color.White)
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                list.forEach { entry ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selected = entry
                            expanded = false

                            onSelectionChanged.invoke(entry)
                        },
                        text = {
                            Text(
                                text = entry,
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


//@Preview(showBackground = true)
//@Composable
//fun SampleSpinner_Preview() {
//    MaterialTheme {
//        val entry1 = Pair("Key1", "Entry1")
//        val entry2 = Pair("Key2", "Entry2")
//        val entry3 = Pair("Key3", "Entry3")
//
//        DemoSpinner(
//            listOf(entry1, entry2, entry3),
//            preselected = entry2,
//            onSelectionChanged = { selected -> /* do something with selected */ },
//            Modifier.wrapContentSize()
//        )
//    }
//}