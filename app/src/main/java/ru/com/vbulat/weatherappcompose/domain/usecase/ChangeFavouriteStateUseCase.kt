package ru.com.vbulat.weatherappcompose.domain.usecase

import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val repository : FavouriteRepository
) {

    suspend fun addToFavourite (city : City) = repository.addToFavourite(city)

    suspend fun removeFromFavourite (cityId: Int) = repository.removeFromFavourite(cityId)
}