package ru.com.vbulat.weatherappcompose.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.com.vbulat.weatherappcompose.domain.entity.City
import ru.com.vbulat.weatherappcompose.presentation.extensions.componentScope
import javax.inject.Inject

class DefaultDetailsComponent @Inject constructor(
    private val city : City,
    onBackClicked : () -> Unit,
    private val storeFactory : DetailsStoreFactory,
    componentContext : ComponentContext
) : DetailsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(city = city) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when(it){
                    DetailsStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    override val model : StateFlow<DetailsStore.State> = store.stateFlow

    override fun onClickBack() {
        store.accept(DetailsStore.Intent.ClickBack)
    }

    override fun onClickFavouriteStatus() {
        store.accept(DetailsStore.Intent.ClickChangeFavouriteStatus)
    }
}