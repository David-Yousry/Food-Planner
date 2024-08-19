package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.MealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.foodplanner.models.Areas
import com.example.foodplanner.models.Meal


var firstTimeAtHome = true
class HomeFragment : Fragment() {

    lateinit var adapter: MealAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var areas: Areas

    private val TAG = "HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: 1")

        recyclerView = view.findViewById(R.id.homeRecycler)

        Log.i(TAG, "onViewCreated: 2")
        lifecycleScope.launch(Dispatchers.IO){

            areas = MealsHelper.service.getAllAreas()
            val meals = MealsHelper.service.getMealsByCategory("Seafood").meals
            Log.i(TAG, "onViewCreated: 3")

            Log.i(TAG, "onViewCreated: 4")


            withContext(Dispatchers.Main){
                Log.i(TAG, "onViewCreated: 5")
                adapter = MealAdapter(meals){ meal ->
                    onMealClick(meal)
                }
                Log.i(TAG, "onViewCreated: 6")
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                Log.i(TAG, "onViewCreated: 7")
                // Show the popup
                if(firstTimeAtHome) {
                    firstTimeAtHome = false
                    showMealOfTheDayPopup(meals.random())
                }
            }
        }



    }

    private fun showMealOfTheDayPopup(meal: Meal) {
        val inflater = LayoutInflater.from(requireContext()) // get inflater from context
        //inflate the layout of teh popup
        val popupView = inflater.inflate(R.layout.popup_meal_of_the_day, this.view as ViewGroup, false)


        val popupWindow = PopupWindow(popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true)

        val mealImage = popupView.findViewById<ImageView>(R.id.mealOfTheDayImage)
        val mealName = popupView.findViewById<TextView>(R.id.mealOfTheDayName)
        val closeButton = popupView.findViewById<Button>(R.id.closePopupButton)

        // Set meal details
        Glide.with(this).load(meal.strMealThumb).into(mealImage)
        mealName.text = meal.strMeal

        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        // Show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    private fun onMealClick(meal: Meal): Int {
        val intent = Intent(requireContext(), MealActivity::class.java)
        intent.putExtra("mealTitle", meal.strMeal)
        startActivity(intent)
        return 0
    }

}