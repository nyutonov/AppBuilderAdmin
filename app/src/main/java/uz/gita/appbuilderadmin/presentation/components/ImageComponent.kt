package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.data.model.ComponentsModel

@Composable
fun ImageComponent(
    uri: String,
    color: Color = Color.Transparent
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color)
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center),
            model = uri.toUri(),
            contentDescription = ""
        )
    }
}