package ru.com.vbulat.weatherappcompose.domain.usecase

import ru.com.vbulat.weatherappcompose.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository : WeatherRepository
) {

    suspend operator fun invoke (cityId:Int) = repository.getWeather(cityId)
}