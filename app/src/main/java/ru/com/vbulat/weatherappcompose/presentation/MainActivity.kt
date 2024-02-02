package ru.com.vbulat.weatherappcompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.com.vbulat.weatherappcompose.data.network.api.ApiFactory
import ru.com.vbulat.weatherappcompose.presentation.ui.theme.WEatherAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather = apiService.loadCurrentWeather(query = "Moscow")
            val forecast = apiService.loadForecast(query = "Moscow")
            val search = apiService.searchCity(query = "Moscow")

            Log.d("AAA", "Current Weather: $currentWeather")
            Log.d("AAA", "Forecast: $forecast")
            Log.d("AAA", "Search cities: $search")
        }

        setContent {
            WEatherAppComposeTheme {

            }
        }
    }
}