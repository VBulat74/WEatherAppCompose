package ru.com.vbulat.weatherappcompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("forecast") val forecastDto : ForecastDto,
    @SerializedName("current") val current : WeatherDto
)
