package com.task.ui.component.news.categoriesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.models.db.Product
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.news.ProductsListViewModel



class ProductsAdapter(private val newsListViewModel: ProductsListViewModel, private val categoryRemotes: List<Product>) : RecyclerView.Adapter<ProductsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(item: Any) {
            newsListViewModel.openNewsDetails(item as Product)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(categoryRemotes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return categoryRemotes.size
    }
}

