package com.example.foodplanner.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.MealActivity
import com.example.foodplanner.R
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.viewModels.WeekFragmentViewModel
import com.example.foodplanner.viewModels.WeekFragmentViewModelFactory
import com.example.foodplanner.views.activities.SignInActivity
import com.example.foodplanner.views.adapters.SmallMealCardAdapter
import com.google.firebase.auth.FirebaseAuth

class WeekMealsFragment : Fragment() {

    lateinit var viewModel: WeekFragmentViewModel

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


        setupViewModel()

        viewModel.getSundayMeals()
        viewModel.getMondayMeals()
        viewModel.getTuesdayMeals()
        viewModel.getWednesdayMeals()
        viewModel.getThursdayMeals()
        viewModel.getFridayMeals()
        viewModel.getSaturdayMeals()



        viewModel.sundayMeals.observe(viewLifecycleOwner) { sundayMeals ->
            if (sundayMeals.isEmpty()) {
                sundayRecycler.visibility = View.GONE
                noPlanSunday.visibility = View.VISIBLE
            } else {
                sundayRecycler.adapter = SmallMealCardAdapter(sundayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.mondayMeals.observe(viewLifecycleOwner) { mondayMeals ->
            if (mondayMeals.isEmpty()) {
                mondayRecycler.visibility = View.GONE
                noPlanMonday.visibility = View.VISIBLE
            } else {
                mondayRecycler.adapter = SmallMealCardAdapter(mondayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.tuesdayMeals.observe(viewLifecycleOwner) { tuesdayMeals ->
            if (tuesdayMeals.isEmpty()) {
                tuesdayRecycler.visibility = View.GONE
                noPlanTuesday.visibility = View.VISIBLE
            } else {
                tuesdayRecycler.adapter = SmallMealCardAdapter(tuesdayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.wednesdayMeals.observe(viewLifecycleOwner) { wednesdayMeals ->
            if (wednesdayMeals.isEmpty()) {
                wednesdayRecycler.visibility = View.GONE
                noPlanWednesday.visibility = View.VISIBLE
            } else {
                wednesdayRecycler.adapter = SmallMealCardAdapter(wednesdayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.thursdayMeals.observe(viewLifecycleOwner) { thursdayMeals ->
            if (thursdayMeals.isEmpty()) {
                thursdayRecycler.visibility = View.GONE
                noPlanThursday.visibility = View.VISIBLE
            } else {
                thursdayRecycler.adapter = SmallMealCardAdapter(thursdayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.fridayMeals.observe(viewLifecycleOwner) { fridayMeals ->
            if (fridayMeals.isEmpty()) {
                fridayRecycler.visibility = View.GONE
                noPlanFriday.visibility = View.VISIBLE
            } else {
                fridayRecycler.adapter = SmallMealCardAdapter(fridayMeals) { meal ->
                    onMealClick(meal)
                }
            }
        }

        viewModel.saturdayMeals.observe(viewLifecycleOwner) { saturdayMeals ->
            if (saturdayMeals.isEmpty()) {
                saturdayRecycler.visibility = View.GONE
                noPlanSaturday.visibility = View.VISIBLE
            } else {
                saturdayRecycler.adapter = SmallMealCardAdapter(saturdayMeals) { meal ->
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

    private fun setupViewModel(){
        val dao = MealsDatabase.getInstance(requireContext()).mealsDao()
        val factory = WeekFragmentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(WeekFragmentViewModel::class.java)
    }


























}