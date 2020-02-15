package com.task.usecase

import androidx.lifecycle.LiveData
import com.task.data.DataSource

import com.task.data.models.db.Category
import com.task.data.models.db.Product
import com.task.data.remote.dto.NewsItem

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext




class NewsUseCase @Inject
constructor(private val dataRepository: DataSource, override val coroutineContext: CoroutineContext) : UseCase, CoroutineScope {


    /**
     * LiveData with formatted tap count.
     */

    /**
     * Public view of tap live data.
     */


    var categoryId:Int=0

    val categoryLiveDataInCase: LiveData<List<Category>> = dataRepository.requestCatgoryFromDataBase()

    var productsLiveData: LiveData<List<Product>> = dataRepository.requestProductsFromDataBase(categoryId)



            override fun getNews() {
        launch {
            try {
              dataRepository.requestCategories()

            } catch (e: Exception) {
            }
        }
    }


    override fun getProducts() {
        launch {
            try {
                dataRepository.requestProductsFromDataBase(categoryId)
            } catch (e: Exception) {
            }
        }
    }


    override fun searchByTitle(keyWord: String): NewsItem? {
        return null
    }

    fun getLiveCategoryFromDataBase() {

        launch {
            dataRepository.requestCategories()
        }


    }


   fun trigger(){
       productsLiveData = dataRepository.requestProductsFromDataBase(categoryId)
    }



}
