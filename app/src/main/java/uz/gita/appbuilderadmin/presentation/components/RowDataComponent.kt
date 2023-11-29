package uz.gita.appbuilderadmin.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.commandiron.wheel_picker_compose.WheelDatePicker
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import java.time.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RowDateComponent(
    date: String = "",
    listener: (String) -> Unit,
    onLongClick: () -> Unit,
    dataaa: ComponentsModel
) {
    val list = if (date.isNotEmpty()) date.split(" ") else listOf()

    Column {

        Row(
            modifier = Modifier.weight(
                if (dataaa.weight == 0f) {
                    1f
                } else {
                    dataaa.weight
                }
            )
        ) {
            WheelDatePicker(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
//                .combinedClickable(onLongClick = onLongClick) { },
                startDate = if (list.isEmpty()) LocalDate.now() else LocalDate.of(
                    list[0].toInt(),
                    list[1].toInt(),
                    list[2].toInt()
                ),
                textColor = Color.LightGray,
                onSnappedDate = {
                    listener("${it.year} ${it.monthValue} ${it.dayOfMonth}")
                }
            )
        }
    }
}