package com.example.foodplanner.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodplanner.MealActivity
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal
import com.example.foodplanner.viewModels.HomeFragmentViewModel
import com.example.foodplanner.views.adapters.HorizMealAdapter

var isFirstTime = true
lateinit var mealOfTheDay: Meal
lateinit var featuredMeals: List<Meal>
class HomeFragment : Fragment() {

    lateinit var mealOfTheDayImg: ImageView
    lateinit var mealOfTheDayName: TextView
    lateinit var featuredRecycler: RecyclerView
    lateinit var mealsNearYouRecycler: RecyclerView
    lateinit var mealOfTheDayContainer: ConstraintLayout
    lateinit var viewModel: HomeFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupViewModel()

        try {
            super.onViewCreated(view, savedInstanceState)

            mealOfTheDayImg = view.findViewById(R.id.mealOfTheDayImg)
            mealOfTheDayName = view.findViewById(R.id.mealOfTheDayText)
            mealOfTheDayContainer = view.findViewById(R.id.mealOfTheDayContainer)
            featuredRecycler = view.findViewById(R.id.featuredRecycler)
            mealsNearYouRecycler = view.findViewById(R.id.mealsAreaRecycler)


            // meal of the DAY
            //TODO fix meal of the day still changing
            if(isFirstTime){
                viewModel.getMealOfTheDay()
            }
            viewModel.mealOfTheDay.observe(viewLifecycleOwner){
                mealOfTheDay = it
                Glide.with(requireContext()).load(mealOfTheDay.strMealThumb)
                    .into(mealOfTheDayImg)
                mealOfTheDayName.text = mealOfTheDay.strMeal

                mealOfTheDayContainer.setOnClickListener {
                    onMealClick(mealOfTheDay)
                }
            }


            // featured meals

            if(isFirstTime){
                viewModel.getFeaturedMeals()
                isFirstTime = false
            }
            viewModel.featuredMeals.observe(viewLifecycleOwner){
                featuredMeals = it
                featuredRecycler.adapter = HorizMealAdapter(featuredMeals) { meal ->
                    onMealClick(meal)
                }
                featuredRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            // meals near you
            //TODO change egypt to user location later

            viewModel.getMealsNearYou()
            viewModel.mealsNearYou.observe(viewLifecycleOwner){
                mealsNearYouRecycler.adapter = HorizMealAdapter(it) { meal ->
                    onMealClick(meal)
                }
                mealsNearYouRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }


        }catch (e: Exception){

        }
    }

    private fun onMealClick(mealHeader: Meal): Int {
        val intent = Intent(requireContext(), MealActivity::class.java)
        intent.putExtra("mealName", mealHeader.strMeal)
        intent.putExtra("from", "home")
        startActivity(intent)

        return 0
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

}