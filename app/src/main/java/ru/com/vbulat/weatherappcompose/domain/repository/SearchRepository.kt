package ru.com.vbulat.weatherappcompose.domain.repository

import ru.com.vbulat.weatherappcompose.domain.entity.City

interface SearchRepository {

    suspend fun search(query : String) : List<City>
}