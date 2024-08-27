package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.MealActivity
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal
import com.example.foodplanner.models.Meals
import com.example.foodplanner.viewModels.SearchMealsViewModel
import com.example.foodplanner.views.adapters.MealAdapter

class SearchMealsActivity : AppCompatActivity() {
    lateinit var meals: Meals
    lateinit var searchTextView : TextView
    lateinit var searchRecycler: RecyclerView
    lateinit var adapter: MealAdapter
    lateinit var backButton: ImageButton
    lateinit var viewModel: SearchMealsViewModel

    private val TAG = "SearchMealsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_meals)

        setupViewModel()


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
                viewModel.getMealsByCategory(name!!)
            }

            else if (from == "ingredient") {
                viewModel.getMealsByIngredient(name!!)
            }

            else {
                viewModel.getMealsByArea(name!!)
            }

            viewModel.meals.observe(this){
                adapter = MealAdapter(it){ meal ->
                    onMealClick(meal)
                }

                searchRecycler.adapter = adapter
                searchRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }

        }
        catch (e : Exception){
        }

    }


    private fun onMealClick(mealHeader: Meal): Int {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("mealName", mealHeader.strMeal)
        intent.putExtra("from", "home")
        startActivity(intent)

        return 0
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(SearchMealsViewModel::class.java)
    }
}





