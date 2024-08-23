package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.models.Meal
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.MealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchBarMealsActivity : AppCompatActivity() {
    lateinit var backBtn : ImageButton
    lateinit var titleText : TextView
    lateinit var mealsRecycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_bar_meals)

        backBtn = findViewById(R.id.backButton)
        titleText = findViewById(R.id.searchMealsTextView)
        mealsRecycler = findViewById(R.id.searchMealsRecycler)

        backBtn.setOnClickListener {
            finish()
        }

        val query = intent.getStringExtra("name")

        try {
            lifecycleScope.launch(Dispatchers.IO){
                val meals = MealsHelper.service.getMealByName(query!!)
                lifecycleScope.launch(Dispatchers.Main){
                    titleText.text = "Search Result for \"$query\""
                    val adapter = MealAdapter(meals.meals){ meal ->
                        onMealClick(meal)
                    }
                    mealsRecycler.adapter = adapter
                    mealsRecycler.layoutManager = LinearLayoutManager(this@SearchBarMealsActivity, LinearLayoutManager.VERTICAL, false)
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
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