package com.example.foodplanner.network

import com.example.foodplanner.models.Areas
import com.example.foodplanner.models.Categories
import com.example.foodplanner.models.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list.php?c=list")
    suspend fun getAllCategories(): Categories
    @GET("list.php?a=list")
    suspend fun getAllAreas(): Areas
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Meals
    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): Meals
    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): Meals
    //get meal by id
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Meals



    //www.themealdb.com/api/json/v1/1/filter.php?c=beef
    //www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
}