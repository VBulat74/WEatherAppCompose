package ru.com.vbulat.weatherappcompose.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.com.vbulat.weatherappcompose.presentation.details.DetailsContent
import ru.com.vbulat.weatherappcompose.presentation.favourite.FavouriteContent
import ru.com.vbulat.weatherappcompose.presentation.search.SearchContent
import ru.com.vbulat.weatherappcompose.presentation.ui.theme.WEatherAppComposeTheme

@Composable
fun RootContent(
    component : RootComponent
) {
    WEatherAppComposeTheme {
        Children(
            stack = component.stack
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }
                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }

}