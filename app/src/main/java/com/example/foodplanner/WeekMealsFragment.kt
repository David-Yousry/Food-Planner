package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.views.adapters.SmallMealCardAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeekMealsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_week_meals, container, false)

        val loginCons = view.findViewById<ConstraintLayout>(R.id.loginToPlanCons)
        val sundayCons = view.findViewById<ConstraintLayout>(R.id.sundayCons)
        val mondayCons = view.findViewById<ConstraintLayout>(R.id.mondayCons)
        val tuesdayCons = view.findViewById<ConstraintLayout>(R.id.tuesdayCons)
        val wednesdayCons = view.findViewById<ConstraintLayout>(R.id.wednesdayCons)
        val thursdayCons = view.findViewById<ConstraintLayout>(R.id.thursdayCons)
        val fridayCons = view.findViewById<ConstraintLayout>(R.id.fridayCons)
        val saturdayCons = view.findViewById<ConstraintLayout>(R.id.saturdayCons)



        val sundayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerSunday)
        val mondayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerMonday)
        val tuesdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerTuesday)
        val wednesdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerWednesday)
        val thursdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerThursday)
        val fridayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerFriday)
        val saturdayRecycler = view.findViewById<RecyclerView>(R.id.planRecyclerSaturday)

        val noPlanSunday = view.findViewById<TextView>(R.id.noPlanSunday)
        val noPlanMonday = view.findViewById<TextView>(R.id.noPlanMonday)
        val noPlanTuesday = view.findViewById<TextView>(R.id.noPlanTuesday)
        val noPlanWednesday = view.findViewById<TextView>(R.id.noPlanWednesday)
        val noPlanThursday = view.findViewById<TextView>(R.id.noPlanThursday)
        val noPlanFriday = view.findViewById<TextView>(R.id.noPlanFriday)
        val noPlanSaturday = view.findViewById<TextView>(R.id.noPlanSaturday)

        val loginToPlanBtn = view.findViewById<Button>(R.id.loginToPlanBtn)

        sundayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mondayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        tuesdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        wednesdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        thursdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fridayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        saturdayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        loginToPlanBtn.setOnClickListener {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
        }


        val myAuth = FirebaseAuth.getInstance()

        if(myAuth.currentUser == null){
            loginCons.visibility = View.VISIBLE
        }else{
            sundayCons.visibility = View.VISIBLE
            mondayCons.visibility = View.VISIBLE
            tuesdayCons.visibility = View.VISIBLE
            wednesdayCons.visibility = View.VISIBLE
            thursdayCons.visibility = View.VISIBLE
            fridayCons.visibility = View.VISIBLE
            saturdayCons.visibility = View.VISIBLE
        }


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

                if(planMeals["Sunday"]!!.isEmpty()){
                    sundayRecycler.visibility = View.GONE
                    noPlanSunday.visibility = View.VISIBLE
                }
                else {
                    sundayRecycler.adapter = SmallMealCardAdapter(planMeals["Sunday"]!!) {
                        onMealClick(it)
                    }
                }

                if(planMeals["Monday"]!!.isEmpty()){
                    mondayRecycler.visibility = View.GONE
                    noPlanMonday.visibility = View.VISIBLE
                }
                else {
                    mondayRecycler.adapter = SmallMealCardAdapter(planMeals["Monday"]!!) {
                        onMealClick(it)
                    }
                }


                if(planMeals["Tuesday"]!!.isEmpty()){
                    tuesdayRecycler.visibility = View.GONE
                    noPlanTuesday.visibility = View.VISIBLE
                }
                else {
                    tuesdayRecycler.adapter = SmallMealCardAdapter(planMeals["Tuesday"]!!) {
                        onMealClick(it)
                    }
                }

                if(planMeals["Wednesday"]!!.isEmpty()){
                    wednesdayRecycler.visibility = View.GONE
                    noPlanWednesday.visibility = View.VISIBLE
                }
                else {
                    wednesdayRecycler.adapter = SmallMealCardAdapter(planMeals["Wednesday"]!!) {
                        onMealClick(it)
                    }
                }

                if(planMeals["Thursday"]!!.isEmpty()){
                    thursdayRecycler.visibility = View.GONE
                    noPlanThursday.visibility = View.VISIBLE
                }
                else {
                    thursdayRecycler.adapter = SmallMealCardAdapter(planMeals["Thursday"]!!) {
                        onMealClick(it)
                    }
                }


                if(planMeals["Friday"]!!.isEmpty()){
                    fridayRecycler.visibility = View.GONE
                    noPlanFriday.visibility = View.VISIBLE
                }
                else {
                    fridayRecycler.adapter = SmallMealCardAdapter(planMeals["Friday"]!!) {
                        onMealClick(it)
                    }
                }


                if(planMeals["Saturday"]!!.isEmpty()){
                    saturdayRecycler.visibility = View.GONE
                    noPlanSaturday.visibility = View.VISIBLE
                }
                else {
                    saturdayRecycler.adapter =SmallMealCardAdapter(planMeals["Saturday"]!!) {
                            onMealClick(it)
                    }
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