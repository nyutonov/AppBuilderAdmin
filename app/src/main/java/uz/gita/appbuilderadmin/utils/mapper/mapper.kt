package uz.gita.appbuilderadmin.utils.mapper

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.getValue
import uz.gita.appbuilderadmin.data.model.ComponentsModel
import java.util.UUID

fun DataSnapshot.toComponentData(): ComponentsModel = ComponentsModel(
    key = key ?: "",

    componentId = child("componentId").getValue(Int::class.java) ?: 0,
    componentsName = child("componentsName").getValue(String::class.java) ?: "",

    isMaxLengthForTextEnabled = child("isMaxLengthForTextEnabled").getValue(Boolean::class.java) ?: false,
    maxLengthForText = child("maxLengthForText").getValue(Int::class.java) ?: 0,
    isMinLengthForTextEnabled = child("isMinLengthForTextEnabled").getValue(Boolean::class.java) ?: false,
    minLengthForText = child("minLengthForText").getValue(Int::class.java) ?: 0,
    isMaxValueForNumberEnabled = child("isMaxValueForNumberEnabled").getValue(Boolean::class.java) ?: false,
    maxValueForNumber = child("maxValueForNumber").getValue(Int::class.java) ?: 0,
    isMinValueForNumberEnabled = child("isMinValueForNumberEnabled").getValue(Boolean::class.java) ?: false,
    minValueForNumber = child("minValueForNumber").getValue(Int::class.java) ?: 0,
    isRequired = child("isRequired").getValue(Boolean::class.java) ?: false,

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