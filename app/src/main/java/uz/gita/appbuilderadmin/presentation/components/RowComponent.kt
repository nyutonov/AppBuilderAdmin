package uz.gita.appbuilderadmin.presentation.componentsimport android.os.Buildimport androidx.annotation.RequiresApiimport androidx.compose.foundation.backgroundimport androidx.compose.foundation.layout.Rowimport androidx.compose.foundation.layout.aspectRatioimport androidx.compose.foundation.layout.fillMaxWidthimport androidx.compose.foundation.layout.heightimport androidx.compose.runtime.Composableimport androidx.compose.ui.Modifierimport androidx.compose.ui.graphics.Colorimport androidx.compose.ui.platform.LocalDensityimport uz.gita.appbuilderadmin.data.model.ComponentsModel@RequiresApi(Build.VERSION_CODES.O)@Composablefun     RowComponent(    componentsModel: List<ComponentsModel>,) {    val density = LocalDensity.current    Row(modifier = Modifier.fillMaxWidth()) {        componentsModel.forEach { component ->            when (component.componentsName) {                "Input" -> {                    RowInputComponent(data = component, modifier = Modifier.weight(component.weight))                }                "Text" -> {                    RowTextComponent(data = component, modifier = Modifier.weight(component.weight))                }                "Selector" -> {                    RowSelectorComponent(data = component, modifier = Modifier.weight(component.weight))                }                "MultiSelector" -> {                    RowMultiSelectorComponent(                        question = component.multiSelectDataQuestion,                        list = component.multiSelectorDataAnswers,                        onLongClick = { },                        onClickDelete = { },                        data = component,                        modifier = Modifier.weight(component.weight)                    )                }                "Date Picker" -> {                    RowDateComponent(                        modifier = Modifier.weight(component.weight), date = "", onLongClick = {},                        listener = { }, dataaa = component                    )                }                "Image" -> {                    RowImageComponent(                        uri = component.imageUri,                        modifier = when (component.selectedImageSize) {                            "Custom" -> {                                Modifier                                    .weight(component.weight)                                    .height(with(density) { component.heightImage.toDp() })                                    .background(Color(component.color))                            }                            "Ratio" -> {                                Modifier                                    .weight(component.weight)                                    .aspectRatio(if (component.aspectRatio == 0f) 1f else component.aspectRatio)                                    .background(Color(component.color))                            }                            else -> {                                Modifier                                    .weight(component.weight)                                    .background(Color(component.color))                            }                        }                    )                }            }        }    }}