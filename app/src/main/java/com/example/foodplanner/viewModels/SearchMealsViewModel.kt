package com.example.foodplanner.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodplanner.models.Meal
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.models.Ingredient
import com.example.foodplanner.network.MealsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchMealsViewModel : ViewModel() {


    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>> = _meals


    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO){
            val res = MealsHelper.service.getMealsByCategory(category)
            withContext(Dispatchers.Main){
                _meals.value = res.meals
                _meals.postValue(res.meals)
            }
        }
    }

    fun getMealsByIngredient(ingredient: String) {
        viewModelScope.launch(Dispatchers.IO){
            val res = MealsHelper.service.getMealsByIngredient(ingredient)
            withContext(Dispatchers.Main){
                _meals.value = res.meals
                _meals.postValue(res.meals)
            }
        }
    }

    fun getMealsByArea(area: String) {
        viewModelScope.launch(Dispatchers.IO){
            val res = MealsHelper.service.getMealsByArea(area)
            withContext(Dispatchers.Main){
                _meals.value = res.meals
                _meals.postValue(res.meals)
            }
        }
    }

}