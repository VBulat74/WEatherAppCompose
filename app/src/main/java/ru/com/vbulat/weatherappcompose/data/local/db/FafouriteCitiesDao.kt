package ru.com.vbulat.weatherappcompose.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.com.vbulat.weatherappcompose.data.local.model.CityDbModel

@Dao
interface FavouriteCitiesDao {

    @Query("SELECT * FROM favourite_ciries")
    fun getFavouriteCities() : Flow<List<CityDbModel>>

    @Query("SELECT EXISTS (SELECT * FROM favourite_ciries WHERE id=:cityId LIMIT 1)")
    fun observeIsFavourite (cityId : Int) : Flow<Boolean>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite (cityDbModel : CityDbModel)

    @Query("DELETE FROM favourite_ciries WHERE id=:cityId")
    suspend fun removeFromeFavourite (cityId : Int)

}