package com.example.foodplanner.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.foodplanner.views.fragments.HomeFragment
import com.example.foodplanner.R
import com.example.foodplanner.views.fragments.SearchFragment
import com.example.foodplanner.views.fragments.WeekMealsFragment
import com.example.foodplanner.databinding.ActivityMainBinding
import com.example.foodplanner.utils.FragmentReplacer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), FragmentReplacer {


    private lateinit var binding: ActivityMainBinding
    private lateinit var profileBtn : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        val replaceFrag = intent.getStringExtra("replaceFrag")

        if(replaceFrag != null) {
            if (replaceFrag == "MyPlans") {
                replaceFragment(WeekMealsFragment())
                bottomNavigationView.setSelectedItemId(R.id.weekMealsBtn)
            }
        }
        else{
            replaceFragment(HomeFragment())
        }

        val myAuth = FirebaseAuth.getInstance()
        if(myAuth.currentUser != null){
            val userPic = myAuth.currentUser!!.photoUrl
            Glide.with(this)
                .load(userPic)
                .into(findViewById(R.id.profileBtn))

        }


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.homeBtn -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.searchBtn -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.weekMealsBtn -> {
                    replaceFragment(WeekMealsFragment())
                    true
                }

                else -> false
            }
        }
        profileBtn = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            if(myAuth.currentUser == null){
                val profileIntent = Intent(this, GuestProfileActivity::class.java)
                startActivity(profileIntent)
            }
            else{
                val profileIntent = Intent(this, UserProfileActivity::class.java)
                startActivity(profileIntent)
            }
        }
    }

    override fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment,fragment)
        transaction.commit()
    }
}