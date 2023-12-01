package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.core.net.toUri
import coil.compose.AsyncImage

@Composable
fun RowImageComponent(
    uri: String,
    modifier: Modifier = Modifier
) {
    var loader by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = uri.toUri(),
            contentDescription = "",
            onLoading = { loader = true },
            onSuccess = { loader = false },
            onError = { loader = false }
        )

        if (loader) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}