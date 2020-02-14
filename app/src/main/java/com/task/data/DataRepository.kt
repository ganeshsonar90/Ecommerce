package com.task.data


import androidx.lifecycle.LiveData
import com.task.data.localdatabase.AppDBHelper
import com.task.data.models.db.Category
import com.task.data.remote.RemoteRepository
import com.task.data.remote.dto.EcommResponse
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository, private val localRepository: AppDBHelper) : DataSource {

    override suspend fun requestNews(): Resource<EcommResponse> {
        return remoteRepository.requestNews()
    }

    override suspend fun requestCatgoryFromDataBase(): LiveData<List<Category>> {
       return localRepository.getAllCategoriesFromDB()
    }


}
