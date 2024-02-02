package ru.com.vbulat.weatherappcompose.domain.repository

import ru.com.vbulat.weatherappcompose.domain.entity.Forecast
import ru.com.vbulat.weatherappcompose.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId : Int) : Weather

    suspend fun getForecast(cityId : Int) : Forecast
}