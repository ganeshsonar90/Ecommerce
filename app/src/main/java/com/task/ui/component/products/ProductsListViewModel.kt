package com.task.ui.component.news

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.Resource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.models.db.Category
import com.task.data.models.db.Product
import com.task.data.remote.dto.CategoryRemote
import com.task.ui.base.BaseViewModel
import com.task.usecase.NewsUseCase
import com.task.usecase.errors.ErrorManager
import com.task.utils.Event
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ProductsListViewModel @Inject
constructor(private val dataRepository: DataSource) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */


    var productsLiveDataIn: LiveData<List<Product>>? = null




    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    private val openNewsDetailsPrivate = MutableLiveData<Event<Product>>()
    val openNewsDetails: LiveData<Event<Product>> get() = openNewsDetailsPrivate

    /**
     * Error handling as UI
     */
    private val showSnackBarPrivate = MutableLiveData<Event<Int>>()
    val showSnackBar: LiveData<Event<Int>> get() = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = showToastPrivate


    fun getNews() {
       // newsDataUseCase.getNews()
    }

    fun openNewsDetails(product: Product) {
        openNewsDetailsPrivate.value = Event(product)
    }

    fun showSnackbarMessage(@StringRes message: Int) {
        showSnackBarPrivate.value = Event(message)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = Event(error.description)
    }

    fun setUp(categoryId: Int) {

        productsLiveDataIn= dataRepository?.requestProductsFromDataBase(categoryId)


    }

}
