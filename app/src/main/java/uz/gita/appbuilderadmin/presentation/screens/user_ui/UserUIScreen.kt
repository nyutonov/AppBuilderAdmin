package uz.gita.appbuilderadmin.presentation.screens.user_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            MainContent(vm.uiState.collectAsState(), name , vm::onEventDispatcher)
        }
    }
}

@Composable
private fun MainContent(
    uiState: State<UserUIContract.UIState>,
    name : String ,
    onEventDispatcher: (UserUIContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
      LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
          items(uiState.value.components){
              when (it.componentsName) {
                  "Text" -> {
                      Spacer(modifier = Modifier.size(10.dp))
                      TextComponent(data = it)
                  }
                  "Input" -> {
                      Spacer(modifier = Modifier.size(10.dp))
                      InputComponent(it)
                  }
                  "Selector" -> {
                      Spacer(modifier = Modifier.size(10.dp))
                      SampleSpinner(it)
                  }
                  "MultiSelector" -> {
                      Spacer(modifier = Modifier.size(10.dp))
                      MultiSelectorComponent(list = it.multiSelectorDataAnswers)
                  }
                  "Date Picker" -> {
                      Spacer(modifier = Modifier.size(10.dp))
                      DateComponent()
                  }
              }
          }
      }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                onEventDispatcher(UserUIContract.Intent.ClickAddComponents(name))
            }) {
            
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