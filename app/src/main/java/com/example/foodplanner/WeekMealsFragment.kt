package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.db.PlanMealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.models.PlanMeal
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.SmallMealCardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeekMealsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_week_meals, container, false)

        val sundayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerSunday)
        val mondayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerMonday)
        val tuesdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerTuesday)
        val wednesdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerWednesday)
        val thursdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerThursday)
        val fridayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerFriday)
        val saturdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerSaturday)

        sundayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mondayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        tuesdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        wednesdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        thursdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fridayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        saturdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        lifecycleScope.launch(Dispatchers.IO){
            val planMealsDao = PlanMealsDatabase.getInstance(requireContext()).plannedMealsDao()
            val planMeals = mapOf(
                "Sunday" to planMealsDao.getPlannedMealsByDay("Sunday"),
                "Monday" to planMealsDao.getPlannedMealsByDay("Monday"),
                "Tuesday" to planMealsDao.getPlannedMealsByDay("Tuesday"),
                "Wednesday" to planMealsDao.getPlannedMealsByDay("Wednesday"),
                "Thursday" to planMealsDao.getPlannedMealsByDay("Thursday"),
                "Friday" to planMealsDao.getPlannedMealsByDay("Friday"),
                "Saturday" to planMealsDao.getPlannedMealsByDay("Saturday")
            )
            withContext(Dispatchers.Main){

                sundayRecycler.adapter = SmallMealCardAdapter(planMeals["Sunday"]!!){ meal ->
                    onMealClick(meal)
                }

                mondayRecycler.adapter = SmallMealCardAdapter(planMeals["Monday"]!!){ meal ->
                    onMealClick(meal)
                }

                tuesdayRecycler.adapter = SmallMealCardAdapter(planMeals["Tuesday"]!!){ meal ->
                    onMealClick(meal)
                }

                wednesdayRecycler.adapter = SmallMealCardAdapter(planMeals["Wednesday"]!!){ meal ->
                    onMealClick(meal)
                }

                thursdayRecycler.adapter = SmallMealCardAdapter(planMeals["Thursday"]!!){ meal ->
                    onMealClick(meal)
                }

                fridayRecycler.adapter = SmallMealCardAdapter(planMeals["Friday"]!!){ meal ->
                    onMealClick(meal)
                }

                saturdayRecycler.adapter = SmallMealCardAdapter(planMeals["Saturday"]!!){ meal ->
                    onMealClick(meal)
                }



            }
        }




        return view
    }

    private fun onMealClick(meal: PlanMeal): Int {
        val intent = Intent(requireContext(), MealActivity::class.java)
        intent.putExtra("mealTitle", meal.strMeal)
        intent.putExtra("intentFrom","Plan")
        startActivity(intent)
        return 0
    }

}