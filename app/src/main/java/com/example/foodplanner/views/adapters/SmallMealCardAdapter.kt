package com.example.foodplanner.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal

class SmallMealCardAdapter(private val meals: List<Meal>) : RecyclerView.Adapter<SmallMealCardAdapter.SmallMealCardViewHolder>() {

    class SmallMealCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        val mealName: TextView = itemView.findViewById(R.id.mealName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallMealCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.small_meal_card, parent, false)
        return SmallMealCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: SmallMealCardViewHolder, position: Int) {
        val meal = meals[position]
        holder.mealName.text = meal.strMeal
         Glide.with(holder.itemView.context).load(meal.strMealThumb).into(holder.mealImage)
    }
}