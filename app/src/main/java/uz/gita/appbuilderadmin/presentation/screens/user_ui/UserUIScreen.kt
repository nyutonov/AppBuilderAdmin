package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UserUIScreen (private val userName:String): AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            val vm: UserUIContract.ViewModel = getViewModel<UserUIViewModel>()
            MainContent(userName = userName,vm.uiState.collectAsState(), vm::onEventDispatcher)
        }
    }
}

@Composable
private fun MainContent(
    userName: String,
    uiState: State<UserUIContract.UIState>,
    onEventDispatcher: (UserUIContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = { onEventDispatcher.invoke(UserUIContract.Intent.ClickAddComponents(userName )) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 20.dp)
        ) {

        }
        LazyColumn {
            items(uiState.value.components) {
                if (it.componentsName == "TextComponent") {
                    TextComponent(data = it)
                } else if (it.componentsName == "InputComponent") {
                    InputComponent(it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    AppBuilderAdminTheme {
//        MainContent()
    }
}