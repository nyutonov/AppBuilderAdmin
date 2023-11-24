package uz.gita.appbuilderadmin.presentation.screens.user_ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.presentation.components.DateComponent
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.MultiSelectorComponent
import uz.gita.appbuilderadmin.presentation.components.SampleSpinner
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UserUIScreen(private val name: String) : AndroidScreen() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            val vm: UserUIContract.ViewModel = getViewModel<UserUIViewModel>()
            vm::onEventDispatcher.invoke(UserUIContract.Intent.LoadData(name))

            MainContent(vm.uiState.collectAsState(), name, vm::onEventDispatcher)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainContent(
    uiState: State<UserUIContract.UIState>,
    name: String,
    onEventDispatcher: (UserUIContract.Intent) -> Unit
) {
    var loaderText by remember {
        mutableStateOf(false)
    }
    loaderText = uiState.value.components.isEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1C2E))
    ) {
        if (uiState.value.loader) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (!(uiState.value.loader) && loaderText) {
            Text(
                text = "Empty",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .background(Color(0xFF0F1C2E))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0XFF1f2b3e))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "User Ui Screen", fontSize = 28.sp, modifier = Modifier.align(
                        Alignment.CenterStart,
                    ),
                    fontFamily = FontFamily.Default,
                    color = Color.White
                )
                Text(
                    text = "+", fontSize = 25.sp, modifier = Modifier
                        .align(
                            Alignment.CenterEnd
                        )
                        .clickable {
                            onEventDispatcher.invoke(
                                UserUIContract.Intent.ClickAddComponents(name)
                            )
                        },
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            LazyColumn {
                items(uiState.value.components) {

                    when (it.componentsName) {
                        "Text" -> {
                            TextComponent(
                                it,
                                onLongClick = {
                                    onEventDispatcher.invoke(
                                        UserUIContract.Intent.DeleteComponents(
                                            it,
                                            name
                                        )
                                    )
                                })
                            textTopComponent(text = "Text")
                        }

                        "Input" -> {
                            textTopComponent(text = "Input")
                            InputComponent(
                                it,
                                onLongClick = {
                                    onEventDispatcher.invoke(
                                        UserUIContract.Intent.DeleteComponents(
                                            it,
                                            name
                                        )
                                    )
                                })
                        }

                        "Selector" -> {
                            textTopComponent(text = "Selector")
                            SampleSpinner(
                                it,
                                onLongClick = {
                                    onEventDispatcher.invoke(
                                        UserUIContract.Intent.DeleteComponents(
                                            it,
                                            name
                                        )
                                    )
                                })
                        }

                        "MultiSelector" -> {
                            textTopComponent(text = "MultiSelector")
                            MultiSelectorComponent(
                                list = it.multiSelectorDataAnswers,
                                onLongClick = {
                                    onEventDispatcher.invoke(
                                        UserUIContract.Intent.DeleteComponents(
                                            it,
                                            name
                                        )
                                    )
                                })
                        }

                        "Date Picker" -> {
                            textTopComponent(text = "Date Picker")
                            //DateComponent(it.datePicker,listener = {}, onLongClick = onEventDispatcher.invoke(UserUIContract.Intent.DeleteComponents(it, name)))
                        }
                    }
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

@Composable
fun textTopComponent(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .background(
                    Color.White
                )
                .align(Alignment.CenterVertically)
        ) {
        }
        Spacer(modifier = Modifier.size(12.dp))
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = text, fontSize = 15.sp, modifier = Modifier.align(
                    Alignment.Center
                ), color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .background(
                    Color.White
                )
                .align(Alignment.CenterVertically)
        ) {
        }
    }
}