package com.task.ui.component.products.categoriesAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.models.db.Product
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

/**
 * C
 */

class ProductsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Product, recyclerItemListener: RecyclerItemListener) {
       // tv_caption.text = newsItem.
        tv_title.text = item.productName
        Picasso.get().load(R.drawable.product).into(iv_item_image)
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}

