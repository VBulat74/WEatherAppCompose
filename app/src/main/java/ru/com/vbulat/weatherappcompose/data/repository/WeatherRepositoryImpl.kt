package ru.com.vbulat.weatherappcompose.data.repository

import ru.com.vbulat.weatherappcompose.data.maper.toEntity
import ru.com.vbulat.weatherappcompose.data.network.api.ApiService
import ru.com.vbulat.weatherappcompose.domain.entity.Forecast
import ru.com.vbulat.weatherappcompose.domain.entity.Weather
import ru.com.vbulat.weatherappcompose.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService : ApiService,
): WeatherRepository {

    override suspend fun getWeather(cityId : Int) : Weather {
        return apiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toEntity()
    }

    override suspend fun getForecast(cityId : Int) : Forecast {
        return apiService.loadForecast("$PREFIX_CITY_ID$cityId").toEntity()
    }

    companion object{
        private const val PREFIX_CITY_ID = "id:"
    }
}