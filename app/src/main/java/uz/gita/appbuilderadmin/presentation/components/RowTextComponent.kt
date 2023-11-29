package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.appbuilderadmin.data.model.ComponentsModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowTextComponent(
    data: ComponentsModel,
    onClick: () -> Unit = {},
    modifier: Modifier
) {


    Column {
        Box(
            modifier = Modifier
                .weight(
                    data.weight
                )
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = data.text,
                    modifier = modifier
                        .padding(start = 2.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.LightGray,
                )
            }
        }
    }
}