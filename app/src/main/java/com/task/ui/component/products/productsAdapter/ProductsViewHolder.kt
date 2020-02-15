package com.task.ui.component.news.categoriesAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.models.db.Product
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ProductsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Product, recyclerItemListener: RecyclerItemListener) {
       // tv_caption.text = newsItem.
        tv_title.text = item.productName
            Picasso.get().load(R.drawable.product).into(iv_news_item_image)
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}

