package uz.gita.appbuilderadmin.data.modeldata class ComponentsModel(    val key: String = "",    val componentId: Int = 0,    val componentsName: String = "",    val input: String = "",    var type: String = "",    val placeHolder: String = "",    val text: String = "",    val color: Int = 0,    val id: String = "",    val visibility: Boolean = false,    val idVisibility: String = "",    val operator: String = "",    val value: String = "",    val list : String ="" ,    val selectorDataQuestion: String = "",    val selectorDataAnswers: List<String> = emptyList(),    val preselected: String = "",    val multiSelectDataQuestion: String = "",    val multiSelectorDataAnswers: List<String> = emptyList(),    val datePicker: String = "")