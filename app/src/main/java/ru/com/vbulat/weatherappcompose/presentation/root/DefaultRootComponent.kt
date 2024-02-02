package ru.com.vbulat.weatherappcompose.presentation.root

import com.arkivanov.decompose.ComponentContext

class DefaultRootComponent (
    componentContext : ComponentContext
) : RootComponent, ComponentContext by componentContext