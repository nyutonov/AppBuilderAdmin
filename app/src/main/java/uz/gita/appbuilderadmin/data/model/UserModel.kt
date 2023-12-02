package uz.gita.appbuilderadmin.data.model

data class UserModel(
    var id: Int,
    var key: String,
    val name : String,
    val password : String
)
