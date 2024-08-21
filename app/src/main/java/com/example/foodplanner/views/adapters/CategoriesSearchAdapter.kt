package com.example.foodplanner.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodplanner.R
import com.example.foodplanner.models.Categories
import com.example.foodplanner.models.Category
import com.example.foodplanner.models.Meal

class CategoriesSearchAdapter(val items: List<Category>, val onClick: (Category) -> Int ) : RecyclerView.Adapter<CategoriesSearchAdapter.CategoriesSearchViewHolder>() {

    class CategoriesSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.searchCardImg)
        val textView: TextView = view.findViewById(R.id.searchCardText)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return CategoriesSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesSearchViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(item.strCategoryThumb).into(holder.imageView)
        holder.textView.text = item.strCategory
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}