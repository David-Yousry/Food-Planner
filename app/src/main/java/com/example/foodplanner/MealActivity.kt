package com.example.foodplanner

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.db.PlanMealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.models.Meals
import com.example.foodplanner.models.PlanMeal
import com.example.foodplanner.network.MealsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealActivity : AppCompatActivity() {

    lateinit var meals : Meals
    lateinit var mealImg : ImageView
    lateinit var mealName : TextView
    lateinit var mealArea : TextView
    lateinit var mealInstructions : TextView
    lateinit var mealIngredients : TextView
    lateinit var mealVideo: WebView
    lateinit var backButton: ImageButton
    lateinit var addToFavBtn : Button
    lateinit var planMealBtn : Button
    var isFav = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_meal)
        enableEdgeToEdge()
        val favMealsDao = MealsDatabase.getInstance(this).mealsDao()
        val plannedMealsDao = PlanMealsDatabase.getInstance(this).plannedMealsDao()



        val mealTitle = intent.getStringExtra("mealTitle")

        mealImg = findViewById(R.id.mealImg)
        mealName = findViewById(R.id.mealName)
        mealArea = findViewById(R.id.mealOrigin)
        mealInstructions = findViewById(R.id.mealSteps)
        mealIngredients = findViewById(R.id.mealIngredients)
        mealVideo = findViewById(R.id.mealVideo)
        addToFavBtn = findViewById(R.id.addToFavButton)
        planMealBtn = findViewById(R.id.planMealButton)
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        lifecycleScope.launch(Dispatchers.IO){
            val favMeals = favMealsDao.getFavMeals()
            meals = MealsHelper.service.getMealByName(mealTitle?:"")
            val meal = meals.meals[0]
            withContext(Dispatchers.Main){
                if(favMeals.contains(meal)){
                    addToFavBtn.text = "Remove from favorites"
                    isFav = true
                }

                mealName.text = mealTitle
                Glide.with(this@MealActivity).load(meal.strMealThumb).into(mealImg)
                mealArea.text = meal.strArea
                mealInstructions.text = meal.strInstructions
                mealIngredients.text =  meal.getIngredients()
                val videoId = meal.strYoutube.split("=").last()
                val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/${videoId}?si=gcBWkDvH8kSXOtdP\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
                mealVideo.loadData(video, "text/html", "utf-8")
                mealVideo.settings.javaScriptEnabled = true
                mealVideo.webChromeClient = WebChromeClient()
            }
        }

        // Add to Favorite button handling
        addToFavBtn.setOnClickListener {
            if(isFav){
                lifecycleScope.launch(Dispatchers.IO){
                    favMealsDao.removeFromFav(meals.meals[0])
                    withContext(Dispatchers.Main){
                        addToFavBtn.text = "Add to favorites"
                        isFav = false
                        Toast.makeText(this@MealActivity, "Meal removed from favorite", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                lifecycleScope.launch(Dispatchers.IO){
                    favMealsDao.addToFav(meals.meals[0])
                    withContext(Dispatchers.Main){
                        addToFavBtn.text = "Remove from favorites"
                        isFav = true
                        Toast.makeText(this@MealActivity, "Meal added to favorite", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //TODO handle plan meal button
        planMealBtn.setOnClickListener {
            val dialog = PlanMealDialogFragment(meals.meals[0]){ day->
                lifecycleScope.launch(Dispatchers.IO){
                    plannedMealsDao.addToPlan(PlanMeal(meals.meals[0].idMeal, day, meals.meals[0].strMeal, meals.meals[0].strMealThumb))
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@MealActivity, "Meal planned for $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dialog.show(supportFragmentManager, "PlanMealDialog")

        }



    }
}