package ru.com.vbulat.weatherappcompose.domain.entity

import java.util.Calendar

data class Weather(
    val tempC : Float,
    val conditionText : String,
    val conditionUrl : String,
    val date: Calendar
)
