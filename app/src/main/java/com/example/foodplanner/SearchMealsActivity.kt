package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.models.Meal
import com.example.foodplanner.models.Meals
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.MealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMealsActivity : AppCompatActivity() {
    lateinit var meals: Meals
    lateinit var searchTextView : TextView
    lateinit var searchRecycler: RecyclerView
    lateinit var adapter: MealAdapter
    lateinit var backButton: ImageButton

    private val TAG = "SearchMealsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_meals)
        try {
            val from = intent.getStringExtra("type")
            val name = intent.getStringExtra("name")

            searchTextView = findViewById(R.id.searchMealsTextView)
            searchRecycler = findViewById(R.id.searchMealsRecycler)
            backButton = findViewById(R.id.backButton)
            backButton.setOnClickListener {
                finish()
            }
            searchTextView.text = name + " Meals"

            if (from == "category") {
                lifecycleScope.launch(Dispatchers.IO){
                    meals = MealsHelper.service.getMealsByCategory(name!!)
                    withContext(Dispatchers.Main){
                        adapter = MealAdapter(meals.meals){ meal ->
                            onMealClick(meal)
                        }

                        searchRecycler.adapter = adapter
                        searchRecycler.layoutManager = LinearLayoutManager(this@SearchMealsActivity, LinearLayoutManager.VERTICAL, false)
                    }
                }
            }

            else if (from == "ingredient") {

                lifecycleScope.launch(Dispatchers.IO){
                    meals = MealsHelper.service.getMealsByIngredient(name!!)
                    withContext(Dispatchers.Main){
                        adapter = MealAdapter(meals.meals){ meal ->
                            onMealClick(meal)
                        }

                        searchRecycler.adapter = adapter
                        searchRecycler.layoutManager = LinearLayoutManager(this@SearchMealsActivity, LinearLayoutManager.VERTICAL, false)
                    }

                }

            }

            else {

                lifecycleScope.launch(Dispatchers.IO){
                    meals = MealsHelper.service.getMealsByArea(name!!)
                    withContext(Dispatchers.Main){
                        adapter = MealAdapter(meals.meals){ meal ->
                            onMealClick(meal)
                        }

                        searchRecycler.adapter = adapter
                        searchRecycler.layoutManager = LinearLayoutManager(this@SearchMealsActivity, LinearLayoutManager.VERTICAL, false)
                    }
                }
            }



        }
        catch (e : Exception){
            Log.i(TAG, "onCAAAAAAAAAAAAAAAAAAAAAAAAAAAAreate:AA AA ->  ${e.message}")
        }

    }


    private fun onMealClick(mealHeader: Meal): Int {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("mealName", mealHeader.strMeal)
        intent.putExtra("from", "home")
        startActivity(intent)

        return 0
    }
}





