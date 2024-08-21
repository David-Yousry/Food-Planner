package com.example.foodplanner.mealsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodplanner.models.Meal

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)
//
//    @Query("SELECT * FROM meals")
//    suspend fun getFavMeals(): List<Meal>
//
//    @Delete
//    suspend fun removeFromFav(meal: Meal) :Int
//
//    // get count of meals in the database
//    @Query("SELECT COUNT(*) FROM meals")
//    suspend fun getFavMealsCount(): Int

    //get meal by name
    @Query("SELECT * FROM meals WHERE strMeal = :mealName")
    suspend fun getMealByName(mealName: String): Meal

    // get meal by id
    @Query("SELECT * FROM meals WHERE idMeal = :mealId")
    suspend fun getMealById(mealId: String): Meal

    // FAV OPERATIONS


    //get all fav meals
    @Query("SELECT * FROM meals WHERE isFav = 1")
    suspend fun getFavMeals(): List<Meal>

    // update is fav status
    @Query("UPDATE meals SET isFav = :isFav WHERE idMeal = :mealId")
    suspend fun updateFavStatus(mealId: String, isFav: Boolean)


    // PLAN MEAL OPERATIONS

    // update meal plan status
    @Query("UPDATE meals SET mealPlans = :mealPlans WHERE idMeal = :mealId")
    suspend fun updateMealPlan(mealId: String, mealPlans: String)

    // get planned meals by day
    @Query("SELECT * FROM meals WHERE mealPlans LIKE '%' || :day || '1%'")
    suspend fun getPlanMealsByDay(day: String): List<Meal>



}