package ru.com.vbulat.weatherappcompose.presentation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.presentation.extensions.componentScope
import javax.inject.Inject

class DefaultFavouriteComponent @Inject constructor(
    private val favouriteStoreFactory : FavouriteStoreFactory,
    private val onCityItemClicked : (City) -> Unit,
    private val onAddFavouriteClicked : () -> Unit,
    private val onSearchClicked : () -> Unit,
    componentContext : ComponentContext
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { favouriteStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is FavouriteStore.Label.CityItemClick -> {
                        onCityItemClicked(it.city)
                    }

                    FavouriteStore.Label.ClickFavourite -> {
                        onAddFavouriteClicked()
                    }

                    FavouriteStore.Label.ClickSearch -> {
                        onSearchClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model : StateFlow<FavouriteStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickAddToFavourite)
    }

    override fun onCityItemClick(city : City) {
        store.accept(FavouriteStore.Intent.CityItemClick(city = city))
    }

}