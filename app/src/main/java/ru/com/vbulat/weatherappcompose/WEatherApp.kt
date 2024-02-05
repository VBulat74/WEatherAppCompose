package ru.com.vbulat.weatherappcompose

import android.app.Application
import ru.com.vbulat.weatherappcompose.di.ApplicationComponent
import ru.com.vbulat.weatherappcompose.di.DaggerApplicationComponent

class WEatherApp : Application() {

    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)

    }
}