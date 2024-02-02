package ru.com.vbulat.weatherappcompose.domain.entity

data class Forecast(
    val currentWeather : Weather,
    val upcoming : List<Weather>,
)
