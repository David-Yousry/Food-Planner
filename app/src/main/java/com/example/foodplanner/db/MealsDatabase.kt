package com.example.foodplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodplanner.mealsDao.MealsDao
import com.example.foodplanner.models.Meal
import com.example.foodplanner.utils.Converters

@Database(entities = [Meal::class], version = 1)
//@TypeConverters(Converters::class)
abstract class MealsDatabase: RoomDatabase() {
    abstract fun mealsDao(): MealsDao
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