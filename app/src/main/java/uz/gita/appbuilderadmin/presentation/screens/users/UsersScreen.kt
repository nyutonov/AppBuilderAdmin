package uz.gita.appbuilderadmin.presentation.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.R
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
            .background(Color(0xFF0F1C2E))
    ) {

        if (uiState.users.isEmpty() && !uiState.progressbar) {
            Image(
                painter = painterResource(id = R.drawable.ic_emptynote),
                contentDescription = null,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

        if (uiState.progressbar && uiState.users.isEmpty()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                LinearProgressIndicator()
                Text(
                    text = "Initializing...",
                    modifier = Modifier
                        .padding(top = 16.dp)
//                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Users",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 28.sp
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .clickable {
                        onEventDispatcher.invoke(UsersContract.Intent.ClickAddUser)
                    },
                tint = Color.White
            )
        }
        
        LazyColumn(modifier = Modifier.padding(top = 56.dp)) {
            items(uiState.users) {
                UserItem(user = it, onLongListener = {
                       onEventDispatcher.invoke(UsersContract.Intent.ClickDelete(it))
                }, onClick = {
                    onEventDispatcher.invoke(
                        UsersContract.Intent.ClickUser(it.name)
                    )
                })
                Spacer(modifier = Modifier.height(10.dp))
            }
        }/*
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
        */
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    AppBuilderAdminTheme {
        MainContent()
    }
}