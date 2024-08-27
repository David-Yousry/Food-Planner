package com.example.foodplanner.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.models.Meal
import com.example.foodplanner.network.MealsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel : ViewModel() {

    private val _mealOfTheDay : MutableLiveData<Meal> = MutableLiveData()
    val mealOfTheDay : MutableLiveData<Meal> = _mealOfTheDay

    private val _featuredMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val featuredMeals : MutableLiveData<List<Meal>> = _featuredMeals

    private val _mealsNearYou : MutableLiveData<List<Meal>> = MutableLiveData()
    val mealsNearYou : MutableLiveData<List<Meal>> = _mealsNearYou

    init {
        getMealOfTheDay()
        getFeaturedMeals()
        getMealsNearYou()
    }

    fun getMealOfTheDay() {
        viewModelScope.launch(Dispatchers.IO){
            val meal = MealsHelper.service.getRandomMeal().meals[0]
            withContext(Dispatchers.Main){
                _mealOfTheDay.postValue(meal)
            }
        }
    }

    fun getFeaturedMeals() {
        viewModelScope.launch(Dispatchers.IO){
            val meals = MealsHelper.service.getMealsByCategory("Seafood").meals
            withContext(Dispatchers.Main){
                _featuredMeals.postValue(meals)
            }
        }
    }

    fun getMealsNearYou() {
        viewModelScope.launch(Dispatchers.IO){
            val meals = MealsHelper.service.getMealsByArea("egyptian").meals
            withContext(Dispatchers.Main){
                _mealsNearYou.postValue(meals)
            }
        }
    }




}