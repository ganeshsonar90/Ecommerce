package com.task.ui.component.home_categories

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.Resource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.models.db.Category
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import com.task.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 */

class CategoriesListViewModel @Inject
constructor(private val dataRepository: DataSource,override val coroutineContext: CoroutineContext) : BaseViewModel(), CoroutineScope {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */

    var categoryLiveDataIn: LiveData<List<Category>> = dataRepository.requestCatgoryFromDataBase()


    private val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    private val openNewsDetailsPrivate = MutableLiveData<Event<Category>>()
    val openNewsDetails: LiveData<Event<Category>> get() = openNewsDetailsPrivate

    /**
     * Error handling as UI
     */
    private val showSnackBarPrivate = MutableLiveData<Event<Int>>()
    val showSnackBar: LiveData<Event<Int>> get() = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = showToastPrivate


    private val updateProgress: MutableLiveData<Any> = MutableLiveData()
    val updateProgressLive: LiveData<Any> get() = updateProgress


    fun getCategories() {
        launch {
            try {
                updateProgress.postValue(Resource.Loading(data = 0))
                var categoryNetworkUpdate = dataRepository.requestCategories()
                updateProgress.postValue(categoryNetworkUpdate)

            } catch (e: Exception) {
            }
        }
    }

    fun openNewsDetails(newsItem: Category) {
        openNewsDetailsPrivate.value = Event(newsItem)
    }

    fun showSnackbarMessage(@StringRes message: Int) {
        showSnackBarPrivate.value = Event(message)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = Event(error.description)
    }

    fun onSearchClick(newsTitle: String) {
       /* if (newsTitle.isNotEmpty()) {
            val newsItem = newsDataUseCase.searchByTitle(newsTitle)
            if (newsItem != null) {
                newsSearchFoundPrivate.value = newsItem
            } else {
                noSearchFoundPrivate.postValue(Unit)
            }
        } else {
            noSearchFoundPrivate.postValue(Unit)
        }*/
    }

   /* fun getCategoryFromDatabase() {
        newsDataUseCase.getLiveCategoryFromDataBase()
    }*/



}
