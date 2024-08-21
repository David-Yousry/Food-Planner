package com.example.foodplanner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.utils.Converters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MealActivity : AppCompatActivity() {

    lateinit var meal: Meal
    lateinit var mealImg: ImageView
    lateinit var mealName: TextView
    lateinit var mealArea: TextView
    lateinit var mealInstructions: TextView
    lateinit var mealIngredients: TextView
    lateinit var mealVideo: WebView
    lateinit var backButton: ImageButton
    lateinit var addToFavBtn: Button
    lateinit var planMealBtn: Button
    private val TAG = "MealActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        enableEdgeToEdge()

        val loadingScreen = findViewById<View>(R.id.loadingScreen)
        val mainContent = findViewById<View>(R.id.mainContent)


        /// ###########################################
        val mealsDao = MealsDatabase.getInstance(this).mealsDao()

        val intentFrom = intent.getStringExtra("from")
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                if (intentFrom == "home") {
                    val mealName = intent.getStringExtra("mealName")
                    if (mealName != null) {
                        meal = mealsDao.getMealByName(mealName) ?: MealsHelper.service.getMealByName(mealName).meals[0]
                    } else {
                        throw IllegalArgumentException("Meal name is null")
                    }
                } else {
                    meal = intent.getSerializableExtra("meal") as Meal
                }

                withContext(Dispatchers.Main) {
                    loadingScreen.visibility = View.GONE
                    mainContent.visibility = View.VISIBLE

                    mealImg = findViewById(R.id.mealImg)
                    mealName = findViewById(R.id.mealName)
                    mealArea = findViewById(R.id.mealOrigin)
                    mealInstructions = findViewById(R.id.mealSteps)
                    mealIngredients = findViewById(R.id.mealIngredients)
                    mealVideo = findViewById(R.id.mealVideo)
                    addToFavBtn = findViewById(R.id.addToFavButton)
                    planMealBtn = findViewById(R.id.planMealButton)
                    backButton = findViewById(R.id.backButton)
                    backButton.setOnClickListener { finish() }

                    if (meal.isFav) {
                        addToFavBtn.text = "Remove from favorites"
                    }
                    mealName.text = meal.strMeal
                    Glide.with(this@MealActivity).load(meal.strMealThumb).into(mealImg)
                    mealArea.text = meal.strArea
                    mealInstructions.text = meal.strInstructions
                    mealIngredients.text = meal.getIngredients()
                    val videoId = meal.strYoutube.split("=").last()
                    val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/${videoId}?si=gcBWkDvH8kSXOtdP\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
                    mealVideo.loadData(video, "text/html", "utf-8")
                    mealVideo.settings.javaScriptEnabled = true
                    mealVideo.webChromeClient = WebChromeClient()

                    // Add to Favorite button handling
                    addToFavBtn.setOnClickListener {
                        lifecycleScope.launch(Dispatchers.IO) {

                            if(meal.mealPlans == null){
                                meal.mealPlans = "Sunday0,Monday0,Tuesday0,Wednesday0,Thursday0,Friday0,Saturday0"
                            }

                            if (meal.isFav) {
                                meal.isFav = false
                                mealsDao.insertMeal(meal)
                                withContext(Dispatchers.Main) {
                                    addToFavBtn.text = "Add to favorites"
                                    Toast.makeText(this@MealActivity, "Meal removed from favorite", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                meal.isFav = true
                                mealsDao.insertMeal(meal)
                                withContext(Dispatchers.Main) {
                                    addToFavBtn.text = "Remove from favorites"
                                    Toast.makeText(this@MealActivity, "Meal added to favorite", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }

                    planMealBtn.setOnClickListener {
                        val dialog = PlanMealDialogFragment(meal) { day ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                meal.mealPlans = meal.mealPlans.replace("${day}0", "${day}1")
                                mealsDao.insertMeal(meal)
                            }
                        }
                        dialog.show(supportFragmentManager, "PlanMealDialog")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading meal data: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MealActivity, "Error loading meal data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}