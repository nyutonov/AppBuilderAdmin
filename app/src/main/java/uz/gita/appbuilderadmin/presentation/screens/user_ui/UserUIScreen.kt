package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UserUIScreen(private val name: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            val viewModel: UserUIContract.ViewModel = getViewModel<UserUIViewModel>()

            viewModel::onEventDispatcher.invoke(UserUIContract.Intent.SetName(name))

            MainContent(
                viewModel.uiState.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun MainContent(
    uiState: UserUIContract.UIState = UserUIContract.UIState(),
    onEventDispatcher: (UserUIContract.Intent) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(uiState.components) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    AppBuilderAdminTheme {
        MainContent()
    }
}