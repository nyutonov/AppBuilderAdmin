package uz.gita.appbuilderadmin.presentation.screens.constructor

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class ConstructorScreen(private val name: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {

        }
    }
}