package com.example.foodplanner.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodplanner.R
import com.example.foodplanner.models.Ingredient

class IngredientsSearchAdapter(private val items: List<Ingredient>, private val onClick: (Ingredient) -> Int) : RecyclerView.Adapter<IngredientsSearchAdapter.IngredientsSearchViewHolder>() {


    class IngredientsSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.searchCardImg)
        val textView: TextView = view.findViewById(R.id.searchCardText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return IngredientsSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsSearchViewHolder, position: Int) {
        val item = items[position]
        item.imgIngredient = "https://www.themealdb.com/images/ingredients/${item.strIngredient}-small.png"
        Glide.with(holder.itemView.context).load(item.imgIngredient).into(holder.imageView)
        holder.textView.text = item.strIngredient
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}