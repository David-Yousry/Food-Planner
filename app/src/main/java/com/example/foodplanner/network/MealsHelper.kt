package com.example.foodplanner.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealsHelper {
    val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = client.create(ApiService::class.java)
}