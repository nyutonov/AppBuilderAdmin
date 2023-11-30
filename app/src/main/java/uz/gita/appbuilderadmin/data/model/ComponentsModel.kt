package uz.gita.appbuilderadmin.data.modeldata class ComponentsModel(    val key: String = "",    val componentId: Int = 0,    val componentsName: String = "",    val input: String = "",    var type: String = "",    val placeHolder: String = "",    val imageUri: String = "",    val color: ULong = 0U,    val heightImage: Float = 0f,    val aspectRatio: Float = 0f,    val selectedImageSize: String = "",    val isMaxLengthForTextEnabled: Boolean = false,    val maxLengthForText: Int = 0,    val isMinLengthForTextEnabled: Boolean = false,    val minLengthForText: Int = 0,    val isMaxValueForNumberEnabled: Boolean = false,    val maxValueForNumber: Int = 0,    val isMinValueForNumberEnabled: Boolean = false,    val minValueForNumber: Int = 0,    val isRequired: Boolean = false,    val text: String = "",    val id: String = "",    val visibility: Boolean = false,    val idVisibility: String = "",    val operator: String = "",    val value: String = "",    val list : String ="" ,    val selectorDataQuestion: String = "",    val selectorDataAnswers: List<String> = emptyList(),    val preselected: String = "",    val multiSelectDataQuestion: String = "",    val multiSelectorDataAnswers: List<String> = emptyList(),    val datePicker: String = "",    val rowType:String="",    val weight:Float=1f,    val lsRow:List<ComponentsModel> = listOf())