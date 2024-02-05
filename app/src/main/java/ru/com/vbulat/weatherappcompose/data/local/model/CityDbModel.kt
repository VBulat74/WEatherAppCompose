package ru.com.vbulat.weatherappcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_ciries")
data class CityDbModel(
    @PrimaryKey val id : Int,
    val name : String,
    val country : String,
)
