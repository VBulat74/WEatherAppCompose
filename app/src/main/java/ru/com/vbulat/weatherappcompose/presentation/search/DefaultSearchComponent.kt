package ru.com.vbulat.weatherappcompose.presentation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.presentation.extensions.componentScope
import javax.inject.Inject

class DefaultSearchComponent @Inject constructor(
    openReason : OpenReason,
    storeFactory : SearchStoreFactory,
    onBackClick : () -> Unit,
    onCitySavedToFavourite  : () -> Unit,
    onForecastForCityRequested : (City) -> Unit,
    componentContext : ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(openReason) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    SearchStore.Label.ClickBack -> {
                        onBackClick()
                    }

                    is SearchStore.Label.OpenForecast -> {
                        onForecastForCityRequested(it.city)
                    }

                    SearchStore.Label.SavedToFavourite -> {
                        onCitySavedToFavourite()
                    }
                }
            }
        }
    }

    override val model : StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query : String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickCity(city : City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }
}