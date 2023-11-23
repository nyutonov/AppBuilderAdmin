package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UserUIScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            MainContent()
        }
    }
}

@Composable
private fun MainContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    AppBuilderAdminTheme {
        MainContent()
    }
}