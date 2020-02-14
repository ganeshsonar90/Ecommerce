package com.task.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.Resource

import com.task.data.error.Error.Companion.NETWORK_ERROR
import com.task.data.models.db.Category
import com.task.data.remote.dto.NewsItem

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsUseCase @Inject
constructor(private val dataRepository: DataSource, override val coroutineContext: CoroutineContext) : UseCase, CoroutineScope {




    var categoryLiveDataInCase: LiveData<List<Category>> ? = null

    // private var categoryLiveData: LiveData<List<Category>>
    // private var categoryLiveDataReso: Resource<LiveData<List<Category>>>


    private val newsMutableLiveData11 = LiveData<List<Category>>()


    private val newsMutableLiveData = MutableLiveData<Resource<List<Category>>>()
    override val newsLiveData: MutableLiveData<Resource<List<Category>>> = newsMutableLiveData



    override fun getNews() {
       // var serviceResponse: Resource<List<Category>>?
        newsMutableLiveData.postValue(Resource.Loading())
        launch {
            try {
                categoryLiveDataInCase = dataRepository.requestCatgoryFromDataBase()

              //  categoryLiveDataIn.


               // categoryLiveDataIn = categoryLiveDataResource.data


                // newsMutableLiveDataVar.data.

              /*  if (newsMutableLiveDataVar?.data.isNullOrEmpty()){
                    Log.e("UseCase","Api called")
                    dataRepository.requestNews()
                }else{
                    Log.e("UseCase","database called")
                    newsMutableLiveData.postValue(serviceResponse)
                }*/

            } catch (e: Exception) {
                newsMutableLiveData.postValue(Resource.DataError(NETWORK_ERROR))
            }
        }
    }

    override fun searchByTitle(keyWord: String): NewsItem? {
        /*val news = newsMutableLiveData.value?.data?.categories.
        if (!news.isNullOrEmpty()) {
            for (newsItem in news) {
                if (newsItem.title.isNotEmpty() && newsItem.title.toLowerCase().contains(keyWord.toLowerCase())) {
                    return newsItem
                }
            }
        }*/
        return null
    }

    fun getLiveCategoryFromDataBase() {

        launch {
            dataRepository.requestNews()
        }


    }
}
