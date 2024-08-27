package com.example.foodplanner.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.models.Meal
import com.example.foodplanner.models.Meals
import com.example.foodplanner.network.MealsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchBarViewModel : ViewModel() {

    private val _searchedMeals: MutableLiveData<List<Meal>> = MutableLiveData()
    val searchedMeals: LiveData<List<Meal>> = _searchedMeals

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message


    fun getSearchedmeals(query: String){
        viewModelScope.launch(Dispatchers.IO){
            val searchedMeals = MealsHelper.service.getMealByName(query!!)
            withContext(Dispatchers.Main) {
                if (searchedMeals.meals != null) {
                    _searchedMeals.postValue(searchedMeals.meals)
                    _message.postValue("Search Result found")
                } else {
                    _searchedMeals.postValue(emptyList())
                    _message.postValue("No Result found")
                }
            }

        }
    }
}
