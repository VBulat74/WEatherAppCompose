package ru.com.vbulat.weatherappcompose.data.repository

import ru.com.vbulat.weatherappcompose.data.maper.toEntities
import ru.com.vbulat.weatherappcompose.data.network.api.ApiService
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService : ApiService
) : SearchRepository {
    override suspend fun search(query : String) : List<City> {
        return apiService.searchCity(query).toEntities()
    }
}