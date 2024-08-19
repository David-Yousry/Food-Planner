package com.example.foodplanner.favoriteFeature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodplanner.models.PlanMeal

@Dao
interface PlannedMealsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToPlan(vararg meal: PlanMeal)

    @Query("SELECT * FROM planned_meals")
    suspend fun getAllPlannedMeals(): List<PlanMeal>

    @Delete
    suspend fun removePlan(meal: PlanMeal) :Int

    // get planned meals for a specific day
    @Query("SELECT * FROM planned_meals WHERE mealDay = :day")
    suspend fun getPlannedMealsByDay(day: String): List<PlanMeal>

    // remove plan meal by day and meal name
    @Query("DELETE FROM planned_meals WHERE mealDay = :day AND idMeal = :mealId")
    suspend fun removePlanByDayAndMealId(day: String, mealId: String): Int

    // get all days that the meal is planned in
    @Query("SELECT mealDay FROM planned_meals WHERE idMeal = :mealId")
    suspend fun getDaysByMealId(mealId: String): List<String>







}