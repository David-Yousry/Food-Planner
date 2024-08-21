package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.favoriteFeature.FavMealsAdapter
import com.example.foodplanner.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteMealsActivity : AppCompatActivity() {
    lateinit var favMealsRecyclerView: RecyclerView
    lateinit var adapter: FavMealsAdapter
    lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_meals)
        favMealsRecyclerView = findViewById(R.id.favMealsRecycler)
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        lifecycleScope.launch(Dispatchers.IO){
            val mealsDao = MealsDatabase.getInstance(this@FavoriteMealsActivity).mealsDao()
            val favMeals = mealsDao.getFavMeals()
            withContext(Dispatchers.Main){
                adapter = FavMealsAdapter(favMeals){ meal ->
                    onMealClick(meal)
                }
                favMealsRecyclerView.adapter = adapter
                favMealsRecyclerView.layoutManager = LinearLayoutManager(this@FavoriteMealsActivity)
            }
        }

    }

    private fun onMealClick(meal: Meal): Int {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("meal", meal)
        startActivity(intent)
        return 0
    }

}