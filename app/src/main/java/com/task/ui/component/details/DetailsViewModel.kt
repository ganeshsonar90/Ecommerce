package com.task.ui.component.details

import androidx.lifecycle.LiveData
import com.task.data.DataSource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.models.db.Product
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import javax.inject.Inject

/**
 *
 */

class DetailsViewModel @Inject
constructor(private val dataRepository: DataSource) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    var productsLiveDataIn: LiveData<Product>? = null


    fun setUp(productId: Int) {
        productsLiveDataIn= dataRepository?.requestProductFromDataBase(productId)


    }
}
