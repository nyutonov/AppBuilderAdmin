package uz.gita.appbuilderadmin.presentation.componentsimport android.util.Logimport androidx.compose.foundation.ExperimentalFoundationApiimport androidx.compose.foundation.backgroundimport androidx.compose.foundation.combinedClickableimport androidx.compose.foundation.layout.fillMaxWidthimport androidx.compose.foundation.layout.paddingimport androidx.compose.foundation.text.KeyboardOptionsimport androidx.compose.material3.Textimport androidx.compose.material3.TextFieldimport androidx.compose.runtime.Composableimport androidx.compose.runtime.getValueimport androidx.compose.runtime.mutableStateOfimport androidx.compose.runtime.rememberimport androidx.compose.runtime.setValueimport androidx.compose.ui.Modifierimport androidx.compose.ui.graphics.Colorimport androidx.compose.ui.text.TextStyleimport androidx.compose.ui.text.input.KeyboardTypeimport androidx.compose.ui.unit.dpimport androidx.compose.ui.unit.spimport uz.gita.appbuilderadmin.data.model.ComponentsModel@OptIn(ExperimentalFoundationApi::class)@Composablefun InputComponent(    data: ComponentsModel,    onClick: () -> Unit = {},    onLongClick: () -> Unit,) {    var value by remember { mutableStateOf("") }    TextField(        value = value,        onValueChange = {            if (data.isEnableMaxLength) {                value = if (it.length > data.maxLength) it.substring(0, data.maxLength) else it            } else {                value = it            }        },        modifier = Modifier            .fillMaxWidth()            .background(Color.Transparent)            .combinedClickable(                onLongClick = onLongClick,                onClick = onClick            )            .padding(20.dp),        placeholder = {            Text(text = data.placeHolder)        },        singleLine = true,        textStyle = TextStyle(fontSize = 18.sp),        keyboardOptions = KeyboardOptions(            keyboardType = when (data.type) {                "Email" -> KeyboardType.Email                "Number" -> KeyboardType.Number                "Phone" -> KeyboardType.Phone                else -> KeyboardType.Text            }        )    )}