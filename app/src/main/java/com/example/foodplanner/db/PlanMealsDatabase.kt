package com.example.foodplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodplanner.favoriteFeature.PlannedMealsDao
import com.example.foodplanner.models.PlanMeal
import com.example.foodplanner.models.Meal

@Database(entities = [PlanMeal::class], version = 1)
abstract class PlanMealsDatabase: RoomDatabase() {
    abstract fun plannedMealsDao(): PlannedMealsDao
    companion object{
        @Volatile
        private var INSTANCE: PlanMealsDatabase? = null
        fun getInstance(context: Context): PlanMealsDatabase{
            return INSTANCE ?: synchronized(this){
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanMealsDatabase::class.java, "plan_meals_database"
                ).build()
                INSTANCE = tempInstance
                tempInstance
            }
        }
    }
}