package ru.com.vbulat.weatherappcompose.data.maper

import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherCurrentDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherForecastDto
import ru.com.vbulat.weatherappcompose.domain.entity.Forecast
import ru.com.vbulat.weatherappcompose.domain.entity.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity() : Weather = current.toEntiy()

fun WeatherDto.toEntiy() : Weather = Weather(
    tempC = tempC,
    conditionText = conditionDto.text,
    conditionUrl = conditionDto.iconUrl.correctImageUrl(),
    date = date.toCalendar()
)

fun WeatherForecastDto.toEntity () : Forecast = Forecast(
    currentWeather = current.toEntiy(),
    upcoming = forecastDto.forecastDay.drop(1).map { dayDto ->
        val dayWeatherDto = dayDto.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionUrl = dayWeatherDto.conditionDto.iconUrl.correctImageUrl(),
            date = dayDto.date.toCalendar()
        )
    }
)

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar * 1_000)
}

private fun String.correctImageUrl() = "https:$this".replace(
    oldValue = "64x64",
    newValue = "128x128"
)