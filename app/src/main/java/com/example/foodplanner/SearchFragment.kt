package com.example.foodplanner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.models.Area
import com.example.foodplanner.models.Category
import com.example.foodplanner.models.Ingredient
import com.example.foodplanner.network.MealsHelper
import com.example.foodplanner.views.adapters.AreaAdapter
import com.example.foodplanner.views.adapters.CategoriesSearchAdapter
import com.example.foodplanner.views.adapters.IngredientsSearchAdapter
import com.example.foodplanner.views.adapters.MealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment() {

    lateinit var categoriesFrame : FrameLayout
    lateinit var ingredientsFrame : FrameLayout
    lateinit var areasFrame : FrameLayout
    lateinit var searchOptionText : TextView
    lateinit var searchRecycler : RecyclerView
    lateinit var catSearchAdapter : CategoriesSearchAdapter
    lateinit var ingredientsSearchAdapter : IngredientsSearchAdapter
    lateinit var areasSearchAdapter : AreaAdapter
    lateinit var searchBar : SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesFrame = view.findViewById(R.id.categoryFrame)
        ingredientsFrame = view.findViewById(R.id.ingredientsFrame)
        areasFrame = view.findViewById(R.id.areaFrame)
        searchOptionText = view.findViewById(R.id.searchResultName)
        searchRecycler = view.findViewById(R.id.searchRecycler)
        searchBar = view.findViewById(R.id.searchBar)


        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(requireContext(), SearchBarMealsActivity::class.java)
                intent.putExtra("name", query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        lifecycleScope.launch(Dispatchers.IO){
            try {
                val categories = MealsHelper.service.getCategories()
                val ingredients = MealsHelper.service.getIngredients()
                val areas = MealsHelper.service.getAllAreas()
                withContext(Dispatchers.Main){
                    searchOptionText.text = "Categories"
                    catSearchAdapter = CategoriesSearchAdapter(categories.categories){ category ->
                        onCategoryClick(category)
                    }
                    searchRecycler.adapter = catSearchAdapter
                    searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    categoriesFrame.setOnClickListener {
                        searchOptionText.text = "Categories"
                        catSearchAdapter = CategoriesSearchAdapter(categories.categories){ category ->
                            onCategoryClick(category)
                        }
                        searchRecycler.adapter = catSearchAdapter
                        searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    }
                    ingredientsFrame.setOnClickListener {
                        searchOptionText.text = "Ingredients"
                        ingredientsSearchAdapter = IngredientsSearchAdapter(ingredients.ingredients){ ingredient ->
                            onIngredientClick(ingredient)
                        }
                        searchRecycler.adapter = ingredientsSearchAdapter
                        searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    areasFrame.setOnClickListener {
                        searchOptionText.text = "Areas"
                        areasSearchAdapter = AreaAdapter(areas.areas){ area ->
                            onAreaClick(area)
                        }
                        searchRecycler.adapter = areasSearchAdapter
                        searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun onCategoryClick(category: Category) : Int{
        val intent = Intent(requireContext(), SearchMealsActivity::class.java)
        intent.putExtra("type", "category")
        intent.putExtra("name", category.strCategory)
        startActivity(intent)
        return 0
    }

    private fun onIngredientClick(ingredient: Ingredient) : Int{
        val intent = Intent(requireContext(), SearchMealsActivity::class.java)
        intent.putExtra("type","ingredient")
        intent.putExtra("name",ingredient.strIngredient)
        startActivity(intent)
        return 0
    }

    private fun onAreaClick(area: Area) : Int{
        val intent = Intent(requireContext(), SearchMealsActivity::class.java)
        intent.putExtra("type","area")
        intent.putExtra("name",area.strArea)
        startActivity(intent)
        return 0
    }

}









