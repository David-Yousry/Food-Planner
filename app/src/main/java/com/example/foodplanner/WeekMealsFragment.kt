package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
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
            val mealsDao = MealsDatabase.getInstance(requireContext()).mealsDao()
            val planMeals = mapOf(
                "Sunday" to mealsDao.getPlanMealsByDay("Sunday"),
                "Monday" to mealsDao.getPlanMealsByDay("Monday"),
                "Tuesday" to mealsDao.getPlanMealsByDay("Tuesday"),
                "Wednesday" to mealsDao.getPlanMealsByDay("Wednesday"),
                "Thursday" to mealsDao.getPlanMealsByDay("Thursday"),
                "Friday" to mealsDao.getPlanMealsByDay("Friday"),
                "Saturday" to mealsDao.getPlanMealsByDay("Saturday")
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

    private fun onMealClick(meal: Meal): Int {
        val intent = Intent(requireContext(), MealActivity::class.java)
        intent.putExtra("meal", meal)
        startActivity(intent)
        return 0
    }

}