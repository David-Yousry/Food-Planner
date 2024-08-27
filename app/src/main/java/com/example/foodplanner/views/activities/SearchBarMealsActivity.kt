package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.MealActivity
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal
import com.example.foodplanner.viewModels.SearchBarViewModel
import com.example.foodplanner.views.adapters.MealAdapter

class SearchBarMealsActivity : AppCompatActivity() {
    lateinit var backBtn : ImageButton
    lateinit var titleText : TextView
    lateinit var mealsRecycler : RecyclerView
    lateinit var viewModel: SearchBarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_bar_meals)

        viewModel = ViewModelProvider(this).get(SearchBarViewModel::class.java)

        backBtn = findViewById(R.id.backButton)
        titleText = findViewById(R.id.searchMealsTextView)
        mealsRecycler = findViewById(R.id.searchMealsRecycler)



        val query = intent.getStringExtra("name")
        titleText.text = "Search Result for \"$query\""

        viewModel.getSearchedmeals(query!!)

        viewModel.searchedMeals.observe(this){
            val adapter = MealAdapter(it){ meal ->
                onMealClick(meal)
            }
            mealsRecycler.adapter = adapter
            mealsRecycler.layoutManager = LinearLayoutManager(this@SearchBarMealsActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter.notifyDataSetChanged()
        }
        viewModel.message.observe(this){
            if(it == "No Result found"){
                Toast.makeText(this, "No Result found", Toast.LENGTH_SHORT).show()
            }
        }


        backBtn.setOnClickListener {
            finish()
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