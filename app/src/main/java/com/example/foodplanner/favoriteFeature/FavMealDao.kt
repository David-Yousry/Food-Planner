package com.example.foodplanner.favoriteFeature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodplanner.models.Meal

@Dao
interface FavMealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFav(vararg meal: Meal)
    @Query("SELECT * FROM meals")
    suspend fun getFavMeals(): List<Meal>
    @Delete
    suspend fun removeFromFav(meal: Meal) :Int
}