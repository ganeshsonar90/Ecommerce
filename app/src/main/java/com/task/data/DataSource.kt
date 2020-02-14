package com.task.data

import androidx.lifecycle.LiveData
import com.task.data.models.db.Category
import com.task.data.remote.dto.EcommResponse

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface DataSource {
    suspend fun requestNews(): Resource<EcommResponse>
    suspend fun requestCatgoryFromDataBase(): LiveData<List<Category>>

}
