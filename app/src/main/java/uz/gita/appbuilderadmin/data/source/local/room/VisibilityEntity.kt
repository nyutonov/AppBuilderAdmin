package uz.gita.appbuilderadmin.data.source.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visibility")
data class VisibilityEntity(
    @PrimaryKey(autoGenerate = true)
    val idValue : Int ,
    val id : String ,
    val type : String ,
    val value : String
)