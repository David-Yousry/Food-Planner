package com.example.foodplanner.viewModels

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

class FavMealsViewModel(private val dao: MealsDao) : ViewModel() {

    private val _meals : MutableLiveData<List<Meal>> = MutableLiveData()
    val meals : MutableLiveData<List<Meal>> get() = _meals

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    init {
        getFavMeals()
    }

    fun getFavMeals(){
        viewModelScope.launch(Dispatchers.IO){
            val list = dao.getFavMeals()
            withContext(Dispatchers.Main){
                if(list.isNullOrEmpty()){
                    _message.postValue("No Favorite Meals")
                }
                else {
                    _meals.postValue(list)
                    _message.postValue("Favorite Meals found")
                }
            }
        }
    }

}

class FavViewModelFactory(private val dao: MealsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavMealsViewModel::class.java)) {
            return FavMealsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
