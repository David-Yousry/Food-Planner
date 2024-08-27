package com.example.foodplanner.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.models.Area
import com.example.foodplanner.models.Category
import com.example.foodplanner.models.Ingredient
import com.example.foodplanner.network.MealsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragmentViewModel : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>> = _categories

    private val _ingredients: MutableLiveData<List<Ingredient>> = MutableLiveData()
    val ingredients: LiveData<List<Ingredient>> = _ingredients

    private val _areas: MutableLiveData<List<Area>> = MutableLiveData()
    val areas: LiveData<List<Area>> = _areas

    init {
//        getCategories()
//        getIngredients()
//        getAreas()
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO){
            val categories = MealsHelper.service.getCategories()
            withContext(Dispatchers.Main){
                _categories.postValue(categories.categories)
            }
        }
    }

    fun getIngredients() {
        viewModelScope.launch(Dispatchers.IO){
            val ingredients = MealsHelper.service.getIngredients()
            withContext(Dispatchers.Main){
                _ingredients.postValue(ingredients.ingredients)
            }
        }
    }

    fun getAreas() {
        viewModelScope.launch(Dispatchers.IO){
            val areas = MealsHelper.service.getAllAreas()
            withContext(Dispatchers.Main){
                _areas.postValue(areas.areas)
            }
        }
    }
}