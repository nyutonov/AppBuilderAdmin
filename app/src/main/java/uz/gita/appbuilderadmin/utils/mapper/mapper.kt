package uz.gita.appbuilderadmin.utils.mapper

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.getValue
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import uz.gita.appbuilderadmin.data.model.VisibilityTypeModule
import uz.gita.appbuilderadmin.data.source.local.room.VisibilityEntity
import java.util.UUID

fun DataSnapshot.toComponentData(): ComponentsModel = ComponentsModel(
    key = key ?: "",

    componentId = child("componentId").getValue(Int::class.java) ?: 0,
    componentsName = child("componentsName").getValue(String::class.java) ?: "",
    maxLength = child("maxLength").getValue(Int::class.java) ?: 0,
    isEnableMaxLength = child("isEnableMaxLength").getValue(Boolean::class.java) ?: false,

    input = child("input").getValue(String::class.java) ?: "",
    type = child("type").getValue(String::class.java) ?: "",
    placeHolder = child("placeHolder").getValue(String::class.java) ?: "",

    text = child("text").getValue(String::class.java) ?: "",
    color = child("color").getValue(Int::class.java) ?: 0,

    selectorDataQuestion = child("selectorDataQuestion").getValue(String::class.java) ?: "",
    selectorDataAnswers = child("selectorDataAnswers").getValue(String::class.java)?.split(":") ?: listOf(),

    multiSelectDataQuestion = child("multiSelectDataQuestion").getValue(String::class.java) ?: "",
    multiSelectorDataAnswers = child("multiSelectorDataAnswers").getValue(String::class.java)?.split(":") ?: listOf(),

    datePicker = child("datePicker").getValue(String::class.java) ?: "",
    visibility = child("visibility").getValue(Boolean::class.java) ?: false,
    idVisibility = child("idVisibility").getValue(String::class.java) ?: "",
    operator = child("operator").getValue(String::class.java) ?: "",
    value = child("value").getValue(String::class.java) ?: "",
    id = child("id").getValue(String::class.java) ?: UUID.randomUUID().toString()
)

fun VisibilityEntity.toData() : VisibilityTypeModule = VisibilityTypeModule(
    id ,
    type ,
    value.split(":")
)

fun VisibilityTypeModule.toEntity() : VisibilityEntity =
    VisibilityEntity(
        0 ,
        id ,
        type ,
        values.joinToString(":")
    )