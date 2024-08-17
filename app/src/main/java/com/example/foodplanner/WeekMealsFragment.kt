package com.example.foodplanner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.db.MealsDatabase
import com.example.foodplanner.models.Meal
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.DayMealsAdapter
import com.example.foodplanner.views.adapters.SmallMealCardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeekMealsFragment : Fragment() {
    lateinit var weekRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_week_meals, container, false)


        weekRecyclerView = view.findViewById(R.id.weekMealsRecyclerView)

        weekRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        weekRecyclerView.adapter = DayMealsAdapter(listOf( "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"))



        return view
    }

}