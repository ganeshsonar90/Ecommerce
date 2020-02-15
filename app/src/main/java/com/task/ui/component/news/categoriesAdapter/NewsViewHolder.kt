package com.task.ui.component.news.categoriesAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.models.db.Category
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Category, recyclerItemListener: RecyclerItemListener) {
       // tv_caption.text = newsItem.
        tv_title.text = item.categoryName
            Picasso.get().load(R.drawable.casuals).into(iv_news_item_image)
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}

