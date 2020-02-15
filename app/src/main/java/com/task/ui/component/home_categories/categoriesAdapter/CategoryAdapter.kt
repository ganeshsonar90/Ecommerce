package com.task.ui.component.home_categories.categoriesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.models.db.Category
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.home_categories.CategoriesListViewModel

/**
 */

class CategoryAdapter(private val categoriesListViewModel: CategoriesListViewModel, private val categoryRemotes: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(item: Any) {
            categoriesListViewModel.openNewsDetails(item as Category)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryRemotes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return categoryRemotes.size
    }
}

