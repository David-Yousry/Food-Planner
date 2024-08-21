
package com.example.foodplanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.foodplanner.utils.Converters
import java.io.Serializable

data class Meals (
    var meals: List<Meal>
)

@Entity(tableName = "meals")
//@TypeConverters(Converters::class)
data class Meal (

    @PrimaryKey
    var idMeal: String,
    var strMeal: String,
    var strMealThumb: String,
    var strCategory: String,
    var strArea: String,
    var strInstructions: String,
//
    var strYoutube: String,
//
//
    var strIngredient1: String,
    var strIngredient2: String,
    var strIngredient3: String,
    var strIngredient4: String,
    var strIngredient5: String,
    var strIngredient6: String,
    var strIngredient7: String,
    var strIngredient8: String,
    var strIngredient9: String,
    var strIngredient10: String,
    var strIngredient11: String,
    var strIngredient12: String,
    var strIngredient13: String,
    var strIngredient14: String,
    var strIngredient15: String,
    var strIngredient16: String,
    var strIngredient17: String,
    var strIngredient18: String,
    var strIngredient19: String,
    var strIngredient20: String,
    var isFav: Boolean,

    var mealPlans : String = "Sunday0,Monday0,Tuesday0,Wednesday0,Thursday0,Friday0,Saturday0"



): Serializable{





    fun getIngredients(): String {
        return listOf(
            strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
        ).filterNotNull().filter { it.isNotEmpty() }.joinToString("\n")
    }
}

