package uz.gita.appbuilderadmin.presentation.screens.constructor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

class ConstructorScreen (
    private val name : String
) : AndroidScreen() {
    @Composable
    override fun Content() {
        ConstructorScreenContent()
    }
}

@Composable
fun ConstructorScreenContent() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1C2E))
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF0F1C2E))
        ){
            Text(
                text = "Constructor",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                    fontWeight = FontWeight.W400,
                    color = Color.White
                )
            )
        }

        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp , Color.LightGray)
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 10.dp , vertical = 10.dp) ,
        ){

        }



    }
}

@Preview(showBackground = true)
@Composable
fun ConstructorScreenPreview( ) {
    ConstructorScreenContent()
}