package ru.com.vbulat.weatherappcompose.presentation.favourite

import com.arkivanov.decompose.ComponentContext

class DefaultFavouriteComponent (
    componentContext : ComponentContext
) : FavouriteComponent, ComponentContext by componentContext