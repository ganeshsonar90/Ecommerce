package com.task.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.models.db.Category
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.EcommResponse

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface UseCase {
    fun getNews()
    fun searchByTitle(keyWord: String): NewsItem?
    val newsLiveData: MutableLiveData<Resource<List<Category>>>

}
