package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultiSelectorComponent(
    list: List<String>,
    onLongClick: () -> Unit
) {
    Column(modifier = Modifier.combinedClickable(onLongClick = onLongClick) { }) {
        list.forEach {
            var check by remember { mutableStateOf(false) }

            Row {
                Checkbox(
                    checked = check,
                    onCheckedChange = { check = it }
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    text = it,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun prev() {
    MultiSelectorComponent(list = listOf("1", "2", "3"))
}