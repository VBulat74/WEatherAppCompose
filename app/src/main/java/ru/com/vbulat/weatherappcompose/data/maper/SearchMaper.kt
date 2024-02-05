package ru.com.vbulat.weatherappcompose.data.maper

import ru.com.vbulat.weatherappcompose.data.network.dto.CityDto
import ru.com.vbulat.weatherappcompose.domain.entity.City

fun CityDto.toEntity() : City = City(id, name, country)

fun List<CityDto>.toEntities() : List<City> = map { it.toEntity() }