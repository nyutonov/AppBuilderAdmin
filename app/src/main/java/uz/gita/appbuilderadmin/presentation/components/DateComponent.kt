package uz.gita.appbuilderadmin.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.wheel_picker_compose.WheelDatePicker
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateComponent(
    date: String = "",
    listener: (String) -> Unit
) {
    val list = if (date.isNotEmpty()) date.split(" ") else listOf()

    WheelDatePicker(
        startDate = if (list.isEmpty()) LocalDate.now() else LocalDate.of(list[0].toInt(), list[1].toInt(), list[2].toInt()),
        textColor = Color.LightGray,
        onSnappedDate = {
            listener("${it.year} ${it.monthValue} ${it.dayOfMonth}")
        }
    )
}