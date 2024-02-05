package ru.com.vbulat.weatherappcompose.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.com.vbulat.weatherappcompose.data.local.db.FavouriteCitiesDao
import ru.com.vbulat.weatherappcompose.data.maper.toDbModel
import ru.com.vbulat.weatherappcompose.data.maper.toEntities
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteCitiesDao : FavouriteCitiesDao
) : FavouriteRepository {

    override val favouritesCities : Flow<List<City>> = favouriteCitiesDao.getFavouriteCities()
        .map { it.toEntities() }

    override fun observeIdFavourite(cityId : Int) : Flow<Boolean> = favouriteCitiesDao
        .observeIsFavourite(cityId)

    override suspend fun addToFavourite(city : City) {
        favouriteCitiesDao.addToFavourite(city.toDbModel())
    }

    override suspend fun removeFromFavourite(cityId : Int) {
        favouriteCitiesDao.removeFromeFavourite(cityId)
    }
}