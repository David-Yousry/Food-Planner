package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.MealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodplanner.models.Areas
import com.example.foodplanner.models.Meal
import com.example.foodplanner.views.adapters.HorizMealAdapter

var isFirstTime = true
lateinit var mealOfTheDay: Meal
lateinit var featuredMeals: MutableList<Meal>
class HomeFragment : Fragment() {

    lateinit var mealOfTheDayImg: ImageView
    lateinit var mealOfTheDayName: TextView
    lateinit var featuredRecycler: RecyclerView
    lateinit var mealsNearYouRecycler: RecyclerView
    lateinit var mealOfTheDayContainer: ConstraintLayout



    private val TAG = "HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            super.onViewCreated(view, savedInstanceState)

            mealOfTheDayImg = view.findViewById(R.id.mealOfTheDayImg)
            mealOfTheDayName = view.findViewById(R.id.mealOfTheDayText)
            mealOfTheDayContainer = view.findViewById(R.id.mealOfTheDayContainer)
            featuredRecycler = view.findViewById(R.id.featuredRecycler)
            mealsNearYouRecycler = view.findViewById(R.id.mealsAreaRecycler)


            // meal of the DAY
            lifecycleScope.launch(Dispatchers.IO) {
                if (isFirstTime) {
                    mealOfTheDay = MealsHelper.service.getRandomMeal().meals[0]
                    // isFirstTime = false (is done in featured meals)
                }

                withContext(Dispatchers.Main) {

                    Glide.with(requireContext()).load(mealOfTheDay.strMealThumb)
                        .into(mealOfTheDayImg)
                    mealOfTheDayName.text = mealOfTheDay.strMeal

                    mealOfTheDayContainer.setOnClickListener {
                        onMealClick(mealOfTheDay)
                    }
                }
            }


            // featured meals

            lifecycleScope.launch(Dispatchers.IO) {
                if(isFirstTime){
                    featuredMeals = mutableListOf()
                    repeat(7) {
                        featuredMeals.add(MealsHelper.service.getRandomMeal().meals[0])
                    }
                    isFirstTime = false
                }
                withContext(Dispatchers.Main) {
                    featuredRecycler.adapter = HorizMealAdapter(featuredMeals) {
                        onMealClick(it)
                    }
                    featuredRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            }


            // meals near you
            //TODO change egypt to user location later
            lifecycleScope.launch(Dispatchers.IO) {
                val mealsNearYou = MealsHelper.service.getMealsByArea("egyptian").meals
                withContext(Dispatchers.Main) {
                    mealsNearYouRecycler.adapter = HorizMealAdapter(mealsNearYou) {
                        onMealClick(it)
                    }
                    mealsNearYouRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            }




        }catch (e: Exception){
            Log.e(TAG, "onViewCreated: ", e)
        }
    }

    private fun onMealClick(mealHeader: Meal): Int {
        val intent = Intent(requireContext(), MealActivity::class.java)
        intent.putExtra("mealName", mealHeader.strMeal)
        intent.putExtra("from", "home")
        startActivity(intent)

        return 0
    }

}