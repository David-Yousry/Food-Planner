package com.example.foodplanner.favoriteFeature;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodplanner.R
import com.example.foodplanner.models.Meal

public class FavMealsAdapter(private val favMeals:List<Meal>, val onClick: (Meal) -> Int) : RecyclerView.Adapter<FavMealsAdapter.FavMealsViewHolder>() {
    class FavMealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.mealName)
        val img: ImageView = itemView.findViewById(R.id.mealImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMealsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_meal_card_mid, parent, false)
        return FavMealsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favMeals.size
    }

    override fun onBindViewHolder(holder: FavMealsViewHolder, position: Int) {
        val meal = favMeals[position]
        holder.name.text = meal.strMeal
        Glide.with(holder.itemView.context).load(favMeals[position].strMealThumb).into(holder.img)
        holder.itemView.setOnClickListener {
            onClick(meal)
        }
    }

}