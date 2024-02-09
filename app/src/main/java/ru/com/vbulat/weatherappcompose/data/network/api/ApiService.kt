package ru.com.vbulat.weatherappcompose.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.com.vbulat.weatherappcompose.data.network.dto.CityDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherCurrentDto
import ru.com.vbulat.weatherappcompose.data.network.dto.WeatherForecastDto

interface ApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ) : WeatherCurrentDto

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount : Int = 5,
    ) : WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ) : List<CityDto>
}