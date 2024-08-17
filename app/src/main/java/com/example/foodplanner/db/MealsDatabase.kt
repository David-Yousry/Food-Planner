package com.example.foodplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodplanner.favoriteFeature.FavMealDao
import com.example.foodplanner.models.Meal

@Database(entities = [Meal::class], version = 1)
abstract class MealsDatabase: RoomDatabase() {
    abstract fun mealsDao(): FavMealDao
    companion object{
        @Volatile
        private var INSTANCE: MealsDatabase? = null
        fun getInstance(context: Context): MealsDatabase{
            return INSTANCE ?: synchronized(this){
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java, "meals_database"
                ).build()
                INSTANCE = tempInstance
                tempInstance
            }
        }
    }

}