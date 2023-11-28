package uz.gita.appbuilderadmin.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import uz.gita.appbuilderadmin.R
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateComponent(
    date: String = "",
    listener: (String) -> Unit,
    onLongClick: () -> Unit,
) {
    val list = if (date.isNotEmpty()) date.split(" ") else listOf()

    Row(modifier = Modifier.fillMaxWidth()) {
        WheelDatePicker(
            modifier = Modifier.weight(1f)
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

        Image(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .clickable { onLongClick() }
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}