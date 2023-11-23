package uz.gita.appbuilderadmin.presentation.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.appbuilderadmin.R

class RegisterScreen : AndroidScreen(){
    @Composable
    override fun Content() {
        val viewModel : RegisterContract.ViewModel = getViewModel<RegisterViewModelImpl>()
        RegisterScreenContent(
            viewModel.uiState.collectAsState().value ,
            viewModel::onEventDispatchers
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    uiState : RegisterContract.UiState ,
    onEventDispatchers : (RegisterContract.Intent) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1C2E))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(141.dp)
                    .height(130.dp),
                painter = painterResource(id = R.drawable.ic_register_screen),
                colorFilter = ColorFilter.tint(Color(0xff4d648d)) ,
                contentDescription = "register icon"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(310.dp)
                        .height(58.dp),
                    value = uiState.name ,
                    placeholder = {
                        Text(
                            text = "Username",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                fontWeight = FontWeight.W400,
                                color = Color.LightGray
                            )
                        )
                    },
                    onValueChange = {
                        onEventDispatchers(RegisterContract.Intent.ChangingName(it))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedTextColor = Color.LightGray ,
                        unfocusedTextColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .width(310.dp)
                        .height(58.dp),
                    value = uiState.password,
                    placeholder = {
                        Text(
                            text = "Password",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                                fontWeight = FontWeight.W400,
                                color = Color.LightGray
                            )
                        )
                    },
                    onValueChange = {
                        onEventDispatchers(RegisterContract.Intent.ChangingPassword(it))
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            onEventDispatchers(RegisterContract.Intent.ChangePasswordState)
                        }) {
                            Icon(
                                modifier = Modifier
                                    .width(17.dp)
                                    .height(17.dp),
                                painter = painterResource(if (uiState.passwordState) R.drawable.ic_eye else R.drawable.hide),
                                contentDescription = "delete word icon",
                                tint = Color(0xFF8E8E93)
                            )

                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedTextColor = Color.LightGray ,
                        unfocusedTextColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(5.dp),
                    visualTransformation = if (uiState.passwordState) VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )
                Button(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(310.dp)
                        .height(50.dp),
                    onClick = {
                        onEventDispatchers(RegisterContract.Intent.ClickRegisterButton)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff4d648d)
                    )
                ) {
                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
