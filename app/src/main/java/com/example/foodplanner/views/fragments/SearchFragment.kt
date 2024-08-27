package com.example.foodplanner.views.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplanner.R
import com.example.foodplanner.models.Area
import com.example.foodplanner.models.Category
import com.example.foodplanner.models.Ingredient
import com.example.foodplanner.viewModels.SearchFragmentViewModel
import com.example.foodplanner.views.activities.SearchBarMealsActivity
import com.example.foodplanner.views.activities.SearchMealsActivity
import com.example.foodplanner.views.adapters.AreaAdapter
import com.example.foodplanner.views.adapters.CategoriesSearchAdapter
import com.example.foodplanner.views.adapters.IngredientsSearchAdapter


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
    lateinit var viewModel : SearchFragmentViewModel


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


        // changing search bar colors ( cant be done from xml)
        val searchAutoCompleteField = SearchView::class.java.getDeclaredField("mSearchSrcTextView")
        searchAutoCompleteField.isAccessible = true
        val searchAutoComplete = searchAutoCompleteField.get(searchBar) as EditText
        searchAutoComplete.setHintTextColor(Color.GRAY)
        searchAutoComplete.setTextColor(Color.BLACK)
        val searchIcon = searchBar.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN) // Replace BLUE with your desired color


        setupViewModel()


        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(requireContext(), SearchBarMealsActivity::class.java)
                intent.putExtra("name", query)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        try {

            viewModel.getCategories()
            viewModel.categories.observe(viewLifecycleOwner){ categories ->
                catSearchAdapter = CategoriesSearchAdapter(categories){ category ->
                    onCategoryClick(category)
                }
                searchRecycler.adapter = catSearchAdapter
                searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            categoriesFrame.setOnClickListener {
                searchOptionText.text = "Categories"
                viewModel.getCategories()
                viewModel.categories.observe(viewLifecycleOwner){ categories ->
                    catSearchAdapter = CategoriesSearchAdapter(categories){ category ->
                        onCategoryClick(category)
                    }
                    searchRecycler.adapter = catSearchAdapter
                    searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

            ingredientsFrame.setOnClickListener {
                searchOptionText.text = "Ingredients"
                viewModel.getIngredients()
                viewModel.ingredients.observe(viewLifecycleOwner){ ingredients ->
                    ingredientsSearchAdapter = IngredientsSearchAdapter(ingredients){ ingredient ->
                        onIngredientClick(ingredient)
                    }
                    searchRecycler.adapter = ingredientsSearchAdapter
                    searchRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

            areasFrame.setOnClickListener {
                searchOptionText.text = "Areas"
                viewModel.getAreas()
                viewModel.areas.observe(viewLifecycleOwner){ areas ->
                    areasSearchAdapter = AreaAdapter(areas){ area ->
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

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
    }

}









