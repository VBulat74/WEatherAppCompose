package ru.com.vbulat.weatherappcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.com.vbulat.weatherappcompose.presentation.ui.theme.WEatherAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WEatherAppComposeTheme {

            }
        }
    }
}