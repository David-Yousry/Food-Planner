package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.math.log

class UserProfileActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var favMeals : ConstraintLayout
    lateinit var backButton: ImageButton
    lateinit var myplans : ConstraintLayout
    lateinit var logOut : ConstraintLayout
    lateinit var userName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_profile)
        enableEdgeToEdge()
        favMeals = findViewById(R.id.favoriteMeal)
        backButton = findViewById(R.id.backButton)
        myplans = findViewById(R.id.myPlan)
        logOut = findViewById(R.id.logout)
        userName = findViewById(R.id.greetingName)

        favMeals.setOnClickListener {
            val intent = Intent(this, FavoriteMealsActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
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