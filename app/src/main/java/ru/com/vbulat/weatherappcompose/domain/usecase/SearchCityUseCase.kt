package ru.com.vbulat.weatherappcompose.domain.usecase

import ru.com.vbulat.weatherappcompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository : SearchRepository
) {

    suspend operator fun invoke (query : String) = repository.search(query)
}