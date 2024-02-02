package ru.com.vbulat.weatherappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.com.vbulat.weatherappcompose.domain.entity.City

interface FavouriteRepository {

    val favouritesCities : Flow<City>

    fun observeIdFavourite(cityId : Int) : Flow<Boolean>

    suspend fun addToFavourite(city : City)

    suspend fun removeFromFavourite(cityId : Int)
}