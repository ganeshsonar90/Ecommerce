package com.task.ui.component.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.models.db.Product
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsViewModel @Inject
constructor(private val dataRepository: DataSource) : BaseViewModel() {
    var productItem: MutableLiveData<NewsItem> = MutableLiveData()
    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    var productsLiveDataIn: LiveData<Product>? = null


    fun setUp(productId: Int) {
        productsLiveDataIn= dataRepository?.requestProductFromDataBase(productId)


    }
}
