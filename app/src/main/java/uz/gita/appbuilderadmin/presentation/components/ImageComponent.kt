package uz.gita.appbuilderadmin.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract

@Composable
fun ImageComponent(
    uri: String,
    color: Color = Color.Transparent,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit,
    uiState: ConstructorContract.UiState,
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color)
    ) {
        var loader by remember {
            mutableStateOf(false)
        }


        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = uri.toUri(),
            contentDescription = "",
            onSuccess = {
                loader = false
            },
            onError = {
                loader = false
            },
            onLoading = {
                loader = true
            }
        )



        Log.d("TTT", "ImageComponent: ${uiState.progressBar}")

        if ((loader || uiState.progressBar)) {
            Log.d("TTT","loader ichiga tushdi ")
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).size(24.dp))
        }

    }
}