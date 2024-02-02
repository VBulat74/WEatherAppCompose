package ru.com.vbulat.weatherappcompose.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.com.vbulat.weatherappcompose.data.network.dto.CityDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherCurrentDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherForecastDto

interface ApiService {

    @GET("current.json?key=xxx")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ) : WeatherCurrentDto

    @GET("forecast.json?key=xxx")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount : Int = 4,
    ) : WeatherForecastDto

    @GET("search.json?key=xxx")
    suspend fun searchCity(
        @Query("q") query: String,
    ) : List<CityDto>
}