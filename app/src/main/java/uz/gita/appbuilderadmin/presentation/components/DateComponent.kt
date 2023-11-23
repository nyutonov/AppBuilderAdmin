package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.wheel_picker_compose.WheelDatePicker
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme
import java.time.LocalDate

@Composable
fun DateComponent() {
    WheelDatePicker(
        textColor = Color.LightGray ,
        onSnappedDate = {

        }
    )
}