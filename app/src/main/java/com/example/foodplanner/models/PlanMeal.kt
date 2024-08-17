package com.example.foodplanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planned_meals")
data class PlanMeal(
    @PrimaryKey
    var idMeal: String,
    var mealDay : String,
    var mealTime : String


)
