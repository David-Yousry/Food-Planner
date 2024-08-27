package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.foodplanner.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class UserProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var favMeals : ConstraintLayout
    private lateinit var backButton: ImageButton
    private lateinit var myplans : ConstraintLayout
    private lateinit var logOut : ConstraintLayout
    private lateinit var userName : TextView
    private lateinit var backupBtn : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_profile)
        enableEdgeToEdge()

        favMeals = findViewById(R.id.favoriteMeal)
        backButton = findViewById(R.id.backButton)
        myplans = findViewById(R.id.myPlan)
        logOut = findViewById(R.id.logout)
        userName = findViewById(R.id.greetingName)
        backupBtn = findViewById(R.id.backUpBtn)

        favMeals.setOnClickListener {
            val intent = Intent(this, FavoriteMealsActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }

        backupBtn.setOnClickListener {
            Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show()
        }

        myplans.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("replaceFrag", "MyPlans")
            startActivity(intent)
        }

        mAuth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val user = mAuth.currentUser
        val username = user?.displayName?.split(" ")?.get(0)
        userName.text = "Hello, $username"

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        logOut.setOnClickListener {
            signOutAndStartSignInActivity()
        }

    }

    private fun signOutAndStartSignInActivity() {
        mAuth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}