package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.foodplanner.databinding.ActivityMainBinding
import com.example.foodplanner.utils.FragmentReplacer

class MainActivity : AppCompatActivity(), FragmentReplacer {


    private lateinit var binding: ActivityMainBinding
    private lateinit var profileBtn : ImageButton

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val replaceFrag = intent.getStringExtra("replaceFrag")
//
//        if(replaceFrag != null) {
//            if (replaceFrag == "MyPlans") {
//                Log.i(TAG, "onCreate: $replaceFrag")
//                replaceFragment(WeekMealsFragment())
//            }
//        }
//        else{
//            replaceFragment(HomeFragment())
//        }


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
            val profileIntent = Intent(this, UserProfileActivity::class.java)
            startActivity(profileIntent)
        }
    }

    override fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment,fragment)
        transaction.commit()
    }
}