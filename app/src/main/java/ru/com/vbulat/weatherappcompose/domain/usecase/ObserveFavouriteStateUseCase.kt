package ru.com.vbulat.weatherappcompose.domain.usecase

import ru.com.vbulat.weatherappcompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository : FavouriteRepository
) {

    operator fun invoke(cityId : Int) = repository.observeIdFavourite(cityId)
}