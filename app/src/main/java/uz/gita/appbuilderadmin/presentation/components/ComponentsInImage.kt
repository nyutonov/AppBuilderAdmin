package uz.gita.appbuilderadmin.presentation.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.core.net.toUri
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract
import uz.gita.appbuilderadmin.presentation.screens.constructor.SetId
import java.io.IOException

@Composable
fun ComponentsInImage(
    uiState: ConstructorContract.UiState,
    onEventDispatchers: (ConstructorContract.Intent) -> Unit,
) {
    val client = OkHttpClient()
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            onEventDispatchers.invoke(ConstructorContract.Intent.ChangeImageUri(it.toString()))
        }



    SetId(uiState = uiState, onEventDispatchers = onEventDispatchers)

    Text(
        text = "Choose",
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
            fontWeight = FontWeight.W400,
            color = Color.White
        )
    )

    Spacer(modifier = Modifier.size(5.dp))

    var expanded by remember { mutableStateOf(false) }

    Box {
        Column {
            OutlinedTextField(
                value = uiState.selectedImageInputType,
                onValueChange = { },
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                readOnly = true,
                colors = TextFieldDefaults.colors(Color.Black)
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                uiState.imageInputTypes.forEach { entry ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            expanded = false

                            onEventDispatchers.invoke(
                                ConstructorContract.Intent.ChangeImageInputType(
                                    entry
                                )
                            )
                        }, text = {
                            Text(
                                text = entry,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.Start),
                                color = Color.Black
                            )
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(onClick = { expanded = !expanded })
        )
    }

    Spacer(modifier = Modifier.size(10.dp))
    if (uiState.selectedImageInputType == "Local") {
        if (uiState.selectedImageUri.isNotEmpty()) {
            uiState.selectedImageUri.apply {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, this.toUri())

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, this.toUri())
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp),
            onClick = { launcher.launch("image/*") },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff4d648d))
        ) {
            Text(
                text = "Select image from device",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }
    } else if (uiState.selectedImageInputType == "Remote") {


        var isCheck by remember { mutableStateOf(false) }

        TextField(
            value = uiState.selectedImageUri,
            onValueChange = { onEventDispatchers.invoke(ConstructorContract.Intent.ChangeImageUri(it)) }
        )

        Button(onClick = {
            isCheck = true
        }) {
            Text(text = "Check")
        }

        if (isCheck) {

            var uri = uiState.selectedImageUri

            if (!(uri.startsWith("https:") || uri.startsWith("http:"))) {
                uri = "https://1"
            }

            val request = Request.Builder()
                .url(uri)
                .head()
                .build()

            val call: Call = client.newCall(request)



            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onEventDispatchers.invoke(ConstructorContract.Intent.ChangeIsExist(false))
                }

                override fun onResponse(call: Call, response: Response) {
                    onEventDispatchers.invoke(ConstructorContract.Intent.ChangeIsExist(response.isSuccessful))

                }
            })
            isCheck = false
        }

        Icon(
            imageVector = if (uiState.isExist) Icons.Default.Done else Icons.Default.Close,
            contentDescription = "",
            tint = if (uiState.isExist){
                Color.Green
            } else{
                Color.Red
            }
        )

    }

    Button(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .width(310.dp)
            .height(50.dp),
        onClick = {
            onEventDispatchers.invoke(ConstructorContract.Intent.ChangeDialogShowing(true))
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff4d648d))
    ) {
        Text(
            text = "Select color",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
        )
    }

    Box(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .width(310.dp)
            .height(50.dp)
            .background(color = uiState.selectedImageColor)
    )
}