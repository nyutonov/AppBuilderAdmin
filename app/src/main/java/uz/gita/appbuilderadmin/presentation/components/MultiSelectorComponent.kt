package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultiSelectorComponent(
    question: String,
    list: List<String>,
    onLongClick: () -> Unit,
    onClickDete: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.combinedClickable(onLongClick = onLongClick) { }) {
            Row {
                Text(
                    text = question,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

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
        Image(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onClickDete() }
                .align(Alignment.TopEnd)
                .padding(end =10.dp, top = 2.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun prev() {
    MultiSelectorComponent("question", listOf(), {}, {})
}