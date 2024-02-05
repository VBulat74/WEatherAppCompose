package ru.com.vbulat.weatherappcompose.data.maper

import ru.com.vbulat.weatherappcompose.data.local.model.CityDbModel
import ru.com.vbulat.weatherappcompose.domain.entity.City

fun City.toDbModel() : CityDbModel {
    return CityDbModel(id, name, country,)
}

fun CityDbModel.ToEntity (): City = City(id,name,country,)

fun List<CityDbModel>.toEntities() : List<City>  = map {
    it.ToEntity()
}