package com.task.ui.base.listeners

import com.task.data.models.db.Category
import com.task.data.remote.dto.CategoryRemote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

interface RecyclerItemListener {
    fun onItemSelected(newsItem: Category)
}
