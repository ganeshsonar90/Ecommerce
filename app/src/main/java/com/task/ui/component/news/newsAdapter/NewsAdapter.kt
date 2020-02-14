package com.task.ui.component.news.newsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.models.db.Category
import com.task.data.remote.dto.CategoryRemote
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.news.NewsListViewModel

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class NewsAdapter(private val newsListViewModel: NewsListViewModel, private val categoryRemotes: List<Category>) : RecyclerView.Adapter<NewsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(newsItem: Category) {
            newsListViewModel.openNewsDetails(newsItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(categoryRemotes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return categoryRemotes.size
    }
}

