package uz.gita.appbuilderadmin.presentation.componentsimport androidx.compose.foundation.layout.fillMaxWidthimport androidx.compose.foundation.layout.paddingimport androidx.compose.foundation.text.KeyboardOptionsimport androidx.compose.material3.Textimport androidx.compose.material3.TextFieldimport androidx.compose.runtime.Composableimport androidx.compose.runtime.getValueimport androidx.compose.runtime.mutableStateOfimport androidx.compose.runtime.rememberimport androidx.compose.runtime.setValueimport androidx.compose.ui.Modifierimport androidx.compose.ui.text.TextStyleimport androidx.compose.ui.text.input.KeyboardTypeimport androidx.compose.ui.unit.dpimport androidx.compose.ui.unit.spimport uz.gita.appbuilderadmin.data.model.ComponentsModel@Composablefun InputComponent(    data: ComponentsModel,) {    var value by remember {        mutableStateOf("")    }    TextField(        value = value,        onValueChange = {           value = it        },        modifier = Modifier            .fillMaxWidth()            .padding(16.dp),        label = { Text("Enter your text") },        placeholder = { Text(text = "PlaceHolder") },        singleLine = true,        textStyle = TextStyle(fontSize = 18.sp),        keyboardOptions = KeyboardOptions(            keyboardType = when (data.type) {                "Email" -> KeyboardType.Email                "Number" -> KeyboardType.Number                else -> KeyboardType.Text            }        )    )}