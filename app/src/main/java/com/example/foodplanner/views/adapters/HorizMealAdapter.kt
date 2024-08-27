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




class HorizMealAdapter(val meals: List<Meal>, val onClick: (Meal) -> Int) : RecyclerView.Adapter<HorizMealAdapter.HorizMealViewHolder>() {

    class HorizMealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.mealName)
        val img: ImageView = itemView.findViewById(R.id.mealImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizMealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_meal_card, parent, false)
        return HorizMealViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizMealViewHolder, position: Int) {
        val meal = meals[position]
        holder.name.text = meal.strMeal
        Glide.with(holder.itemView.context).load(meals[position].strMealThumb).into(holder.img)
        holder.itemView.setOnClickListener {
            onClick(meal)
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }

}