package ru.com.vbulat.weatherappcompose.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.com.vbulat.weatherappcompose.data.local.db.FavouriteCitiesDao
import ru.com.vbulat.weatherappcompose.data.local.db.FavouriteDatabase
import ru.com.vbulat.weatherappcompose.data.network.api.ApiFactory
import ru.com.vbulat.weatherappcompose.data.network.api.ApiService
import ru.com.vbulat.weatherappcompose.data.repository.FavouriteRepositoryImpl
import ru.com.vbulat.weatherappcompose.data.repository.SearchRepositoryImpl
import ru.com.vbulat.weatherappcompose.data.repository.WeatherRepositoryImpl
import ru.com.vbulat.weatherappcompose.domain.repository.FavouriteRepository
import ru.com.vbulat.weatherappcompose.domain.repository.SearchRepository
import ru.com.vbulat.weatherappcompose.domain.repository.WeatherRepository

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl : FavouriteRepositoryImpl) : FavouriteRepository

    @[ApplicationScope Binds]
    fun bindWeatherRepository(impl : WeatherRepositoryImpl) : WeatherRepository

    @[ApplicationScope Binds]
    fun bindSearchRepository(impl : SearchRepositoryImpl) : SearchRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideApiService() : ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideFavouritesDatabase(context : Context) : FavouriteDatabase {
            return FavouriteDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun provideFavouritesCitiesDao(database : FavouriteDatabase) : FavouriteCitiesDao {
            return database.favouriteCitiesDao()
        }
    }
}