package com.example.foodplanner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.foodplanner.db.PlanMealsDatabase
import com.example.foodplanner.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanMealDialogFragment(private val meal : Meal, private val onDaySelected: (String) -> Unit) : DialogFragment() {

    private lateinit var mealDays: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_plan_meal, container, false)
        val plannedMealsDao = PlanMealsDatabase.getInstance(requireContext()).plannedMealsDao()

        lifecycleScope.launch(Dispatchers.IO){
            mealDays = plannedMealsDao.getDaysByMealId(meal.idMeal)
            withContext(Dispatchers.Main){
                for (day in mealDays){
                    when(day){
                        // change each button color to forest green if the meal is already planned for that day
                        "Sunday" -> view.findViewById<Button>(R.id.sundayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Monday" -> view.findViewById<Button>(R.id.mondayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Tuesday" -> view.findViewById<Button>(R.id.tuesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Wednesday" -> view.findViewById<Button>(R.id.wednesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Thursday" -> view.findViewById<Button>(R.id.thursdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Friday" -> view.findViewById<Button>(R.id.fridayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Saturday" -> view.findViewById<Button>(R.id.saturdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))

                    }
                }
            }
        }

        view.findViewById<Button>(R.id.sundayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Sunday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Sunday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Sunday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.sundayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Sunday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.sundayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for sunday", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            dismiss()
        }
        view.findViewById<Button>(R.id.mondayButton).setOnClickListener {

            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Monday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Monday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Monday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.mondayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Monday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.mondayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Monday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.tuesdayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Tuesday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Tuesday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Tuesday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.tuesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Tuesday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.tuesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Tuesday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.wednesdayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Wednesday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Wednesday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Wednesday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.wednesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Wednesday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.wednesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Wednesday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.thursdayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Thursday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Thursday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Thursday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.thursdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Thursday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.thursdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Thursday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.fridayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Friday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Friday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Friday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.fridayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Friday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.fridayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Friday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.saturdayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){
                if(mealDays.contains("Saturday")) {
                    plannedMealsDao.removePlanByDayAndMealId("Saturday", meal.idMeal)
                    isNowPlanned = false
                }
                else {
                    onDaySelected("Saturday")
                    isNowPlanned = true
                }
                withContext(Dispatchers.Main){
                    if(isNowPlanned){
                        view.findViewById<Button>(R.id.saturdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        Toast.makeText(requireContext(), "Meal planned for Saturday", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        view.findViewById<Button>(R.id.saturdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                        Toast.makeText(requireContext(), "Meal plan removed for Saturday", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }

        return view
    }
}