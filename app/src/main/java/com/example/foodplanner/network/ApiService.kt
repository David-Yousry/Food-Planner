package com.example.foodplanner.network

import com.example.foodplanner.models.Areas
import com.example.foodplanner.models.Categories
import com.example.foodplanner.models.Ingredient
import com.example.foodplanner.models.Ingredients
import com.example.foodplanner.models.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list.php?a=list")
    suspend fun getAllAreas(): Areas

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Meals

    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): Meals

    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): Meals

    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): Meals
    //get meal by id
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Meals

    // list all meal categories
    @GET("categories.php")
    suspend fun getCategories(): Categories

    // get all ingredients
    @GET("list.php?i=list")
    suspend fun getIngredients(): Ingredients

    //get random meal
    @GET("random.php")
    suspend fun getRandomMeal(): Meals
}