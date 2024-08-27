package com.example.foodplanner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext








// ############################################################ MVVM will be applied in the next version ######################################################


class PlanMealDialogFragment(private val meal : Meal, private val onDaySelected: (String) -> Unit) : DialogFragment() {

    private lateinit var mealDays: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_plan_meal, container, false)
        val mealsDao = MealsDatabase.getInstance(requireContext()).mealsDao()

        lifecycleScope.launch(Dispatchers.IO){
            if(meal.mealPlans == null){
                meal.mealPlans = "Sunday0,Monday0,Tuesday0,Wednesday0,Thursday0,Friday0,Saturday0"
                mealsDao.insertMeal(meal)
            }
            mealDays = meal.mealPlans.split(",")
            withContext(Dispatchers.Main){
                for (day in mealDays){
                    when(day){
                        // change each button color to forest green if the meal is already planned for that day
                        "Sunday1" -> view.findViewById<Button>(R.id.sundayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Monday1" -> view.findViewById<Button>(R.id.mondayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Tuesday1" -> view.findViewById<Button>(R.id.tuesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Wednesday1" -> view.findViewById<Button>(R.id.wednesdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Thursday1" -> view.findViewById<Button>(R.id.thursdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Friday1" -> view.findViewById<Button>(R.id.fridayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))
                        "Saturday1" -> view.findViewById<Button>(R.id.saturdayButton).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.forest_green))

                    }
                }
            }
        }

        view.findViewById<Button>(R.id.sundayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){

                if(meal.mealPlans.contains("Sunday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Sunday1", "Sunday0")
                    mealsDao.insertMeal(meal)
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
                        Toast.makeText(requireContext(), "Meal plan removed for Sunday", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            dismiss()
        }
        view.findViewById<Button>(R.id.mondayButton).setOnClickListener {
            var isNowPlanned = false
            lifecycleScope.launch(Dispatchers.IO){

                if(meal.mealPlans.contains("Monday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Monday1", "Monday0")
                    mealsDao.insertMeal(meal)
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
                if(meal.mealPlans.contains("Tuesday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Tuesday1", "Tuesday0")
                    mealsDao.insertMeal(meal)
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
                if(meal.mealPlans.contains("Wednesday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Wednesday1", "Wednesday0")
                    mealsDao.insertMeal(meal)
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
                if(meal.mealPlans.contains("Thursday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Thursday1", "Thursday0")
                    mealsDao.insertMeal(meal)
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
                if(meal.mealPlans.contains("Friday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Friday1", "Friday0")
                    mealsDao.insertMeal(meal)
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

                if(meal.mealPlans.contains("Saturday1")) {
                    meal.mealPlans = meal.mealPlans.replace("Saturday1", "Saturday0")
                    mealsDao.insertMeal(meal)
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