package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.foodplanner.R

class GuestProfileActivity : AppCompatActivity() {
    lateinit var loginBtn: ConstraintLayout
    lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_profile)
        loginBtn = findViewById(R.id.backUpBtn)
        backButton = findViewById(R.id.backButton)

        loginBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        backButton.setOnClickListener {
            finish()
        }


    }
}