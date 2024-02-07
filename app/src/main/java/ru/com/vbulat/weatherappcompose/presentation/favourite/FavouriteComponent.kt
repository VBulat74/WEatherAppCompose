package ru.com.vbulat.weatherappcompose.presentation.favourite

import kotlinx.coroutines.flow.StateFlow
import ru.com.vbulat.weatherappcompose.domain.entity.City

interface FavouriteComponent {

    val model : StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onClickAddFavourite()

    fun onCityItemClick(city : City)
}