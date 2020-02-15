package com.task.ui.component.home_categories.categoriesAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.models.db.Category
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

/**
 *
 */

class CategoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Category, recyclerItemListener: RecyclerItemListener) {
       // tv_caption.text = newsItem.
        tv_title.text = item.categoryName
        Picasso.get().load(R.drawable.casuals).into(iv_item_image)
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}

