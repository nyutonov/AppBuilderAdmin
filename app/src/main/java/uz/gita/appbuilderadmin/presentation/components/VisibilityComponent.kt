package uz.gita.appbuilderadmin.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.appbuilderadmin.R
import uz.gita.appbuilderadmin.data.model.VisibilityModule
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract
import uz.gita.appbuilderadmin.utils.extensions.myToast

@SuppressLint("MutableCollectionMutableState")
@Composable
fun VisibilityComponents(
    uiState : ConstructorContract.UiState ,
    onEventDispatchers : (ConstructorContract.Intent) -> Unit
) {

    val regexSign = "[^<>|=]|<<|>>|<<|=<|=>".toRegex()


    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = uiState.visibilityState,
            onCheckedChange = {
                onEventDispatchers(ConstructorContract.Intent.ClickVisibilityState)
            } ,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xff4d648d)
            )
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "Set Visibility",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = if (uiState.visibilityState) Color.White else Color.Gray
            )
        )
    }
    if (uiState.visibilityState) {
        uiState.listVisibilitiesValue.forEach {
            Spacer(modifier = Modifier.size(10.dp))
            TextVisibility(
                id = it.componentId,
                operator = it.operator,
                value = it.value
            )
        }
    }
    if (uiState.visibilityState) {
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp),
            onClick = {
                onEventDispatchers(ConstructorContract.Intent.ClickVisibilityAddButton)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff4d648d)
            )
        ) {
            Text(
                text = "Add Visibility",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
    if (uiState.visibilityState&& uiState.addButtonVisibilityState) {
        onEventDispatchers(ConstructorContract.Intent.LoadData)
        val list = listOf(
            "Input" ,
            "Selector" ,
            "Multi Selector" ,
            "in" ,
            "!in"
        )
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = list,
            preselected = "select" ,
            onSelectionChanged = {
               onEventDispatchers(ConstructorContract.Intent.OnChangeVisibilityComponentState(it))
            },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .height(56.dp)
        ) {

        }
    }
    if(uiState.visibilityState && uiState.visibilityComponentState == "in") {
        onEventDispatchers(ConstructorContract.Intent.LoadData)
        onEventDispatchers(ConstructorContract.Intent.ChangingOperator("in"))
        MyText(value = "Choose Components")
        val list = listOf(
            "Input" ,
            "Selector"
        )
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = list,
            preselected = "select component",
            onSelectionChanged = {
                  onEventDispatchers(ConstructorContract.Intent.OnChangeChoseComponent(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        if (uiState.choseComponent == "Input") {
            Spacer(modifier = Modifier.size(10.dp))
            DemoSpinner(
                list = uiState.listAllInputId,
                preselected = "select input id",
                onSelectionChanged = {
                    onEventDispatchers(ConstructorContract.Intent.ChangingComponentId(it))
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {

            }
        }else if (uiState.choseComponent == "Selector") {
            Spacer(modifier = Modifier.size(10.dp))
            DemoSpinner(
                list = uiState.listAllSelectorId,
                preselected = "select selector id",
                onSelectionChanged = {
                    onEventDispatchers(ConstructorContract.Intent.ChangeSelectedSelectorId(it))
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {

            }
        }
            MyText(value = "Choose Multi Selector Id")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.listAllMultiSelectorId,
            preselected = "select id",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.OnChangeInMultiSelectorId(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        MyText(value = "Value")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.selectedMultiSelectorList,
            preselected = "select value",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.OnChangeInMultiSelectorValue(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
    }
    else if (uiState.visibilityState && uiState.visibilityComponentState == "!in") {
        onEventDispatchers(ConstructorContract.Intent.LoadData)
        onEventDispatchers(ConstructorContract.Intent.ChangingOperator("!in"))
        MyText(value = "Choose Components")
        val list = listOf(
            "Input" ,
            "Selector"
        )
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = list,
            preselected = "select component",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.OnChangeChoseComponent(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        if (uiState.choseComponent == "Input") {
            Spacer(modifier = Modifier.size(10.dp))
            DemoSpinner(
                list = uiState.listAllInputId,
                preselected = "select input id",
                onSelectionChanged = {
                    onEventDispatchers(ConstructorContract.Intent.ChangingComponentId(it))
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {

            }
        }else if (uiState.choseComponent == "Selector") {
            Spacer(modifier = Modifier.size(10.dp))
            DemoSpinner(
                list = uiState.listAllSelectorId,
                preselected = "select selector id",
                onSelectionChanged = {
                    onEventDispatchers(ConstructorContract.Intent.ChangeSelectedSelectorId(it))
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {

            }
        }
        MyText(value = "You need to enter values")
        var valuesList by remember {
            mutableStateOf(listOf<String>())
        }
        val cloneList = ArrayList<String>()
        cloneList.addAll(valuesList)
        valuesList.forEachIndexed{index , value ->
            Spacer(modifier = Modifier.size(10.dp))
            MyTextField(
                value = valuesList[index],
                listener =  {
                    cloneList[index] = it
                    valuesList = cloneList
                    onEventDispatchers(ConstructorContract.Intent.OnChangeInList(valuesList))
                }
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp),
            onClick = {
                cloneList.add("")
                valuesList = cloneList
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff4d648d)
            )
        ) {
            Text(
                text = "Add Value",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }

    }
    else if(uiState.visibilityState && uiState.visibilityComponentState == "Input") {
        MyText(value = "Component ID")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.listAllInputId,
            preselected = "select id",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingComponentId(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        MyText(value = "Operator")
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "You can add only <>=",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        )
        if (uiState.operator.contains("<|>".toRegex())) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "You enter < or >. If you enter this we can check visibility only number. REMEMBER",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                    fontWeight = FontWeight.W400,
                    color = Color.White ,
                    textAlign = TextAlign.Center
                )
            )
        }
        val list = listOf(
            "==" ,
            "!=" ,
            "<=" ,
            ">=" ,
            "<" ,
            ">"  ,
        )
        DemoSpinner(
            list = list,
            preselected = list[0],
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingOperator(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

        }
        MyText(value = "Value")
        MyTextField(
            value = uiState.visibilityValue,
            listener = {
                onEventDispatchers(
                    ConstructorContract.Intent.ChangingVisibilityValue(it)
                )
                onEventDispatchers(
                    ConstructorContract.Intent.ChangeVisibilityCheck
                )
            },
            outlinedTextFieldColors = if (!uiState.visibilityCheck)
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedTextColor = Color.Red,
                    unfocusedTextColor = Color.Red
                )
            else OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray
            ) ,
            check = uiState.operator.contains("<|>".toRegex())
        )
    } else if (uiState.visibilityState && uiState.visibilityComponentState == "Selector") {

        MyText(value = "Component ID")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.listAllSelectorId,
            preselected = "select id",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangeSelectedSelectorId(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        MyText(value = "Operator")
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "You can add only <>=",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        )
        if (uiState.operator.contains("<|>".toRegex())) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "You enter < or >. If you enter this we can check visibility only number. REMEMBER",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.helvetica))),
                    fontWeight = FontWeight.W400,
                    color = Color.White ,
                    textAlign = TextAlign.Center
                )
            )
        }
        val list = listOf(
            "==" ,
            "!="
        )
        DemoSpinner(
            list = list,
            preselected = "select operator",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingOperator(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

        }
        MyText(value = "Value")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.selectedSelectorList,
            preselected = "select value",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingVisibilityValue(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }

    }
    else if (uiState.visibilityState && uiState.visibilityComponentState == "Multi Selector") {

        MyText(value = "Component ID")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.listAllMultiSelectorId,
            preselected = "select id",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangeSelectedMultiSelectorId(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }
        MyText(value = "Operator")
        Spacer(modifier = Modifier.size(10.dp))

        val list = listOf(
            "==" ,
            "!="
        )
        DemoSpinner(
            list = list,
            preselected = list[0],
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingOperator(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

        }
        MyText(value = "Value")
        Spacer(modifier = Modifier.size(10.dp))
        DemoSpinner(
            list = uiState.selectedMultiSelectorList,
            preselected = "select value",
            onSelectionChanged = {
                onEventDispatchers(ConstructorContract.Intent.ChangingVisibilityValue(it))
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {

        }

    }
    if (uiState.visibilityState && uiState.addButtonVisibilityState) {
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(310.dp)
                .height(50.dp),
            onClick = {
                Log.d("TTT" ,"visibility value : ${uiState.visibilityValue} operator : ${uiState.operator} ")
                if (uiState.visibilityComponentState.isEmpty()) {
                    myToast("You need to enter all data")
                }
                 if (uiState.visibilityComponentState == "Input"  ) {
                     if(uiState.operator.isEmpty() || uiState.visibilityValue.isEmpty()) {
                         myToast("You need to enter all data")
                     }else {
                         onEventDispatchers(ConstructorContract.Intent.ClickAddButtonVisibility)
                     }
                }
                 else if(uiState.visibilityComponentState == "Selector" ) {
                     if( uiState.visibilityValue.isEmpty()) {
                         myToast("You need to enter all data")
                     }else {
                         onEventDispatchers(ConstructorContract.Intent.ClickAddButtonVisibility)
                     }
                }else if(uiState.visibilityComponentState == "Multi Selector") {
                    if(uiState.visibilityValue.isEmpty()) {
                        myToast("You need to enter all data")
                    }else {
                        onEventDispatchers(ConstructorContract.Intent.ClickAddButtonVisibility)
                    }
                } else {
                    onEventDispatchers(ConstructorContract.Intent.ClickAddButtonVisibility)
                 }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff4d648d)
            )
        ) {
            Text(
                text = "Add",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }
        Spacer(modifier = Modifier.size(7.dp))
    }

}