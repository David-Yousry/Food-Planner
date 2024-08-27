package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.MealActivity
import com.example.foodplanner.R
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.favoriteFeature.FavMealsAdapter
import com.example.foodplanner.models.Meal
import com.example.foodplanner.viewModels.FavViewModelFactory
import com.example.foodplanner.viewModels.FavMealsViewModel

class FavoriteMealsActivity : AppCompatActivity() {
    private lateinit var favMealsRecyclerView: RecyclerView
    lateinit var adapter: FavMealsAdapter
    lateinit var backButton: ImageButton
    lateinit var viewModel: FavMealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_meals)

        favMealsRecyclerView = findViewById(R.id.favMealsRecycler)
        backButton = findViewById(R.id.backButton)

        setupViewModel()
        viewModel.getFavMeals()

        viewModel.meals.observe(this){
            adapter = FavMealsAdapter(it){ meal ->
                onMealClick(meal)
            }
            favMealsRecyclerView.adapter = adapter
            favMealsRecyclerView.layoutManager = LinearLayoutManager(this)
            adapter.notifyDataSetChanged()
        }
        viewModel.message.observe(this){
            if(it == "No Favorite Meals"){
                Toast.makeText(this, "No Favorite Meals", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun onMealClick(meal: Meal): Int {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("meal", meal)
        startActivity(intent)
        return 0
    }

    private fun setupViewModel(){
        val dao = MealsDatabase.getInstance(this).mealsDao()
        val factory = FavViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(FavMealsViewModel::class.java)
    }

}