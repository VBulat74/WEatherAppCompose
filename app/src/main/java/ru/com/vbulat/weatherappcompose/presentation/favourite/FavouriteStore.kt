package ru.com.vbulat.weatherappcompose.presentation.favourite

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.domain.usecase.GetCurrentWeatherUseCase
import ru.com.vbulat.weatherappcompose.domain.usecase.GetFavouriteCitiesUseCase
import ru.com.vbulat.weatherappcompose.presentation.favourite.FavouriteStore.Intent
import ru.com.vbulat.weatherappcompose.presentation.favourite.FavouriteStore.Label
import ru.com.vbulat.weatherappcompose.presentation.favourite.FavouriteStore.State
import javax.inject.Inject

interface FavouriteStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickSearch : Intent

        data object ClickFavourite : Intent

        data class CityItemClick(
            val city : City
        ) : Intent
    }

    data class State(
        val cityItems : List<CityItem>
    ) {

        data class CityItem(
            val city : City,
            val weatherState : WeatherState,
        )


        sealed interface WeatherState {

            data object Initial : WeatherState

            data object Loading : WeatherState

            data object Error : WeatherState

            data class Loaded(
                val tempC : Float,
                val iconUrl : String,
            ) : WeatherState

        }
    }

    sealed interface Label {

        data object ClickSearch : Label

        data object ClickFavourite : Label

        data class CityItemClick(
            val city : City
        ) : Label
    }
}

class FavouriteStoreFactory @Inject constructor(
    private val storeFactory : StoreFactory,
    private val getFavouriteCitiesUseCase : GetFavouriteCitiesUseCase,
    private val getCurrentWeatherUseCase : GetCurrentWeatherUseCase,
) {

    fun create() : FavouriteStore =
        object : FavouriteStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class FavouriteCitiesLaded(val cities : List<City>) : Action
    }

    private sealed interface Msg {

        data class FavouriteCitiesLaded(val cities : List<City>) : Msg

        data class WeatherLoaded(
            val cityId : Int,
            val tempC : Float,
            val conditionIconUrl : String
        ) : Msg

        data class WEaterLoadingError(val cityId : Int) : Msg

        data class WEaterIsLoading(val cityId : Int) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getFavouriteCitiesUseCase().collect {
                    dispatch(Action.FavouriteCitiesLaded(it))
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent : Intent, getState : () -> State) {
            when (intent) {
                is Intent.CityItemClick -> {
                    publish(Label.CityItemClick(intent.city))
                }
                Intent.ClickFavourite -> {
                    publish(Label.ClickFavourite)
                }
                Intent.ClickSearch -> {
                    publish(Label.ClickSearch)
                }
            }
        }

        override fun executeAction(action : Action, getState : () -> State) {

            when (action) {
                is Action.FavouriteCitiesLaded -> {
                    val cities = action.cities
                    dispatch(Msg.FavouriteCitiesLaded(cities))
                    cities.forEach {
                        scope.launch {
                            loadWeatherForCity(it)
                        }
                    }
                }
            }
        }

        private suspend fun loadWeatherForCity(city : City) {
            dispatch(Msg.WEaterIsLoading(city.id))
            try {
                val weather = getCurrentWeatherUseCase(city.id)
                dispatch(
                    Msg.WeatherLoaded(
                        cityId = city.id,
                        tempC = weather.tempC,
                        conditionIconUrl = weather.conditionUrl
                    )
                )
            } catch (e : Exception) {
                dispatch(Msg.WEaterLoadingError(city.id))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg : Msg) : State = when (msg) {
            is Msg.FavouriteCitiesLaded -> {
                copy(
                    cityItems = msg.cities.map {
                        State.CityItem(
                            city = it,
                            weatherState = State.WeatherState.Initial
                        )
                    }
                )
            }
            is Msg.WEaterIsLoading -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Loading)
                        } else {
                            it
                        }
                    }
                )

            }
            is Msg.WEaterLoadingError -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Error)
                        } else {
                            it
                        }
                    }
                )

            }
            is Msg.WeatherLoaded -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Loaded(
                                msg.tempC,
                                msg.conditionIconUrl
                            ))
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }
}
