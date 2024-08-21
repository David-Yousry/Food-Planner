
package com.example.foodplanner.models

import com.google.gson.annotations.SerializedName

data class Ingredients (
    @SerializedName("meals")
    val ingredients: List<Ingredient>
)

data class Ingredient (
    val strDescription: String? = null,
    val strIngredient: String,
    val idIngredient: String,
    var imgIngredient : String,
    val strType: String? = null
)
