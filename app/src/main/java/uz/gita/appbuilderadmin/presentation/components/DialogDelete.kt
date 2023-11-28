package uz.gita.appbuilderadmin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.ui.theme.AppBuilderAdminTheme

@Composable
fun DeleteDialog(
    clickYes: () -> Unit,
    clickNo: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color(0xFF252525), shape = RoundedCornerShape(size = 20.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.info),
            contentDescription = "InfoDialog",
            modifier = Modifier
                .padding(top = 28.dp)
                .size(36.dp)
                .align(TopCenter)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 33.dp)
                .padding(horizontal = 15.dp)
                .align(BottomCenter)
        ) {
            Box(
                modifier = Modifier
//                    .padding(start = 38.dp, bottom = 33.dp)
                    .weight(2f)
                    .clickable { clickNo() }
                    .height(39.dp)
//                    .align(BottomStart)
                    .background(color = Color(0xFFFF0000), shape = RoundedCornerShape(size = 5.dp))

            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunitoextralight)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier.align(Center)
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Box(
                modifier = Modifier
//                    .padding(end = 33.dp, bottom = 33.dp)
                    .weight(2f)
                    .clickable { clickYes() }
                    .height(39.dp)
                    .background(color = Color(0xFF30BE71), shape = RoundedCornerShape(size = 5.dp))

            ) {
                Text(
                    text = "Delete",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunitoextralight)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),

                        textAlign = TextAlign.Center,
                    ), modifier = Modifier.align(Center)
                )
            }

        }
        Text(
            text = "Do you want to delete it?",
            style = TextStyle(
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.nunitoextralight)),
                fontWeight = FontWeight(400),
                color = Color(0xFFCFCFCF),
                textAlign = TextAlign.Center,
            ), modifier = Modifier.align(Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppBuilderAdminTheme {
        DeleteDialog({}, {})
    }
}