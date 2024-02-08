package ru.com.vbulat.weatherappcompose.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.com.vbulat.weatherappcompose.presentation.details.DetailsComponent
import ru.com.vbulat.weatherappcompose.presentation.favourite.FavouriteComponent
import ru.com.vbulat.weatherappcompose.presentation.search.SearchComponent

interface RootComponent {

    val stack : Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite (val component : FavouriteComponent) : Child

        data class Search (val component : SearchComponent) : Child

        data class Details (val component : DetailsComponent) : Child
    }
}