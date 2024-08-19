package com.example.foodplanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Plans (
    var plans: List<PlanMeal>
)


@Entity(tableName = "planned_meals")
data class PlanMeal(

    var idMeal: String,
    var mealDay : String,
    var strMeal: String,
    var strMealThumb: String,

//    var strCategory: String,
//    var strArea: String,
//    var strInstructions: String,
////
//    var strYoutube: String,
////
////
//    var strIngredient1: String,
//    var strIngredient2: String,
//    var strIngredient3: String,
//    var strIngredient4: String,
//    var strIngredient5: String,
//    var strIngredient6: String,
//    var strIngredient7: String,
//    var strIngredient8: String,
//    var strIngredient9: String,
//    var strIngredient10: String,
//    var strIngredient11: String,
//    var strIngredient12: String,
//    var strIngredient13: String,
//    var strIngredient14: String,
//    var strIngredient15: String,
//    var strIngredient16: String,
//    var strIngredient17: String,
//    var strIngredient18: String,
//    var strIngredient19: String,
//    var strIngredient20: String,
//
//    var isFav: Boolean,
//
    @PrimaryKey
    var mealString : String = idMeal+mealDay,
){
//    fun getIngredients(): String {
//        return listOf(
//            strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
//            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
//            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
//            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
//        ).filterNotNull().filter { it.isNotEmpty() }.joinToString("\n")
//    }
}


