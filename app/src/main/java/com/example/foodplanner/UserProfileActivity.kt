package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class UserProfileActivity : AppCompatActivity() {
    lateinit var favMeals : ConstraintLayout
    lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_profile)
        enableEdgeToEdge()
        favMeals = findViewById(R.id.favoriteMeal)
        backButton = findViewById(R.id.backButton)

        favMeals.setOnClickListener {
            val intent = Intent(this, FavoriteMealsActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}