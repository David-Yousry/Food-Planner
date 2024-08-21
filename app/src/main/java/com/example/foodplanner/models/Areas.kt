package com.example.foodplanner.models

import com.google.gson.annotations.SerializedName

data class Areas (
    @SerializedName("meals")
    val areas: List<Area>
)


data class Area (
    val strArea: String
)