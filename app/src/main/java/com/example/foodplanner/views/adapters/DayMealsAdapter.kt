package com.example.foodplanner.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal

class DayMealsAdapter(private val days: List<String>) : RecyclerView.Adapter<DayMealsAdapter.DayMealsViewHolder>() {

    //, val onClick: (Meal) -> Int
    class DayMealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.dayText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayMealsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.week_meals_cards, parent, false)
        return DayMealsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: DayMealsViewHolder, position: Int) {
        holder.dayText.text = days[position]
//        holder.itemView.setOnClickListener {
//
//        }
    }
}