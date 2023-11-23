package uz.gita.appbuilderadmin.presentation.screens.constructor

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.presentation.components.InputComponent
import uz.gita.appbuilderadmin.presentation.components.TextComponent
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme
import uz.mlsoft.mydemosforquiz.ui.components.DemoSpinner

class ConstructorScreen (
    private val name : String
) : AndroidScreen() {
    @Composable
    override fun Content() {
        ConstructorScreenContent()
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun ConstructorScreenContent() {

    val list = listOf(
        Pair("key0" , "component") ,
        Pair("key1" , "Input") ,
        Pair("key2" , "Text") ,
        Pair("key3" , "Selector") ,
        Pair("key4" , "MultiSelector") ,
        Pair("key5" , "Date Picker")
    )
    var selectedComponent by remember {
        mutableStateOf(list[0])
    }
    var inputType by remember {
        mutableStateOf("")
    }

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
                modifier = Modifier
                    .align(Alignment.Center) ,
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
                .border(2.dp, Color.LightGray)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp) ,
        ){

            when(selectedComponent.second) {
                "Input" -> {
                    InputComponent(data = ComponentsModel(
                        type = inputType
                    ))
                }
                "Text" -> {
//                    TextComponent()
                }
            }

        }


        Text(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 10.dp),
            text = "Components",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        )



        DemoSpinner(
            list = list,
            preselected = selectedComponent,
            onSelectionChanged = {
                selectedComponent = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ConstructorScreenPreview( ) {
    ConstructorScreenContent()
}