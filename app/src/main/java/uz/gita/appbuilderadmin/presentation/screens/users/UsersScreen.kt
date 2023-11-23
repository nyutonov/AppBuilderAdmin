package uz.gita.appbuilderadmin.presentation.screens.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.presentation.components.UserItem
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UsersScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            val viewModel: UsersContract.ViewModel = getViewModel<UsersViewModel>()

            MainContent(
                viewModel.uiState.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun MainContent(
    uiState: UsersContract.UIState = UsersContract.UIState(),
    onEventDispatcher: (UsersContract.Intent) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(uiState.users) {
                UserItem(name = it) {
                    onEventDispatcher.invoke(UsersContract.Intent.ClickUser(it))
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = {
                onEventDispatcher.invoke(UsersContract.Intent.ClickAddUser)
            }
        ) {
            Text(text = "+")
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