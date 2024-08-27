package com.example.foodplanner.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.mealsDao.MealsDao
import com.example.foodplanner.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeekFragmentViewModel(private val dao: MealsDao) : ViewModel() {

    private val _sundayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val sundayMeals : LiveData<List<Meal>> = _sundayMeals

    private val _mondayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val mondayMeals : LiveData<List<Meal>> get() = _mondayMeals

    private val _tuesdayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val tuesdayMeals : LiveData<List<Meal>> get() = _tuesdayMeals

    private val _wednesdayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val wednesdayMeals : LiveData<List<Meal>> get() = _wednesdayMeals

    private val _thursdayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val thursdayMeals : LiveData<List<Meal>> get() = _thursdayMeals

    private val _fridayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val fridayMeals : LiveData<List<Meal>> get() = _fridayMeals

    private val _saturdayMeals : MutableLiveData<List<Meal>> = MutableLiveData()
    val saturdayMeals : LiveData<List<Meal>> get() = _saturdayMeals

    init {
        getSundayMeals()
        getMondayMeals()
        getTuesdayMeals()
        getWednesdayMeals()
        getThursdayMeals()
        getFridayMeals()
        getSaturdayMeals()
    }

    fun getSundayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Sunday")
            withContext(Dispatchers.Main){
                if(meals.isNullOrEmpty()){
                    _sundayMeals.postValue(emptyList())
                }
                else {
                    _sundayMeals.postValue(meals)
                }
            }
        }
    }

    fun getMondayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Monday")
            withContext(Dispatchers.Main){
                _mondayMeals.value = meals
            }
        }
    }

    fun getTuesdayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Tuesday")
            withContext(Dispatchers.Main){
                _tuesdayMeals.value = meals
            }
        }
    }

    fun getWednesdayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Wednesday")
            withContext(Dispatchers.Main){
                _wednesdayMeals.value = meals
            }
        }
    }

    fun getThursdayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Thursday")
            withContext(Dispatchers.Main){
                _thursdayMeals.value = meals
            }
        }
    }

    fun getFridayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Friday")
            withContext(Dispatchers.Main){
                _fridayMeals.value = meals
            }
        }
    }

    fun getSaturdayMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val meals = dao.getPlanMealsByDay("Saturday")
            withContext(Dispatchers.Main){
                _saturdayMeals.value = meals
            }
        }
    }

}

class WeekFragmentViewModelFactory(private val dao: MealsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeekFragmentViewModel::class.java)){
            return WeekFragmentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


























