package ru.com.vbulat.weatherappcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.com.vbulat.weatherappcompose.WEatherApp
import ru.com.vbulat.weatherappcompose.presentation.root.DefaultRootComponent
import ru.com.vbulat.weatherappcompose.presentation.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory : DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState : Bundle?) {

        (applicationContext as WEatherApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}