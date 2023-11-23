package uz.gita.appbuilderadmin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorScreen
import uz.gita.appbuilderadmin.presentation.screens.users.UsersScreen
import uz.gita.appbuilderadmin.utils.navigator.NavigationHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator (
                screen = UsersScreen()
            ){ navigator ->
                navigationHandler.navigationFlow
                    .onEach {
                        it(navigator)
                    }
                    .launchIn(lifecycleScope)

                CurrentScreen()
            }
        }
    }
}