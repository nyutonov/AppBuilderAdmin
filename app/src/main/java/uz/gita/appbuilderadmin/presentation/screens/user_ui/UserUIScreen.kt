package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.presentation.components.DateComponent
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.MultiSelectorComponent
import uz.gita.appbuilderadmin.presentation.components.SampleSpinner
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class UserUIScreen(private val name: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        AppBuilderAdminTheme {
            val vm: UserUIContract.ViewModel = getViewModel<UserUIViewModel>()
            vm.onEventDispatcher(UserUIContract.Intent.LoadData(name))
            MainContent(vm.uiState.collectAsState(), vm::onEventDispatcher)
        }
    }
}

@Composable
private fun MainContent(
    uiState: State<UserUIContract.UIState>,
    onEventDispatcher: (UserUIContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
      LazyColumn{
          items(uiState.value.components){
             if (it.componentsName=="TextComponent"){
                 TextComponent(data = it)
             }else if (it.componentsName=="InputComponent"){
                 InputComponent(it)
             }else if (it.componentsName=="Selector"){
                 SampleSpinner(it)
             }else if(it.componentsName=="Multi selector"){
                 MultiSelectorComponent(list = it.multiSelectorDataAnswers)
             }else if (it.componentsName=="Date picker"){
                 DateComponent()
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