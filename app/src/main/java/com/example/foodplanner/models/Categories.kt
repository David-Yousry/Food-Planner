
package com.example.foodplanner.models

data class Categories (
    val categories: List<Category>
)

data class Category (
    val strCategory: String,
    val strCategoryDescription: String,
    val idCategory: String,
    val strCategoryThumb: String
)
