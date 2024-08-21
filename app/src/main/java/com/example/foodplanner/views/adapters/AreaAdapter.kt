package com.example.foodplanner.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.R
import com.example.foodplanner.models.Area

class AreaAdapter(private val items: List<Area>, private val onClick: (Area) -> Int) : RecyclerView.Adapter<AreaAdapter.AreaViewHolder>() {

    class AreaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.searchCardText)
        val imageView: ImageView = view.findViewById(R.id.searchCardImg)
        fun imgInvalid() {
            imageView.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return AreaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.strArea
        holder.imgInvalid()
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}