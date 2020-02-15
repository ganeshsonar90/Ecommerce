package com.task.data

import androidx.lifecycle.LiveData
import com.task.data.models.db.Category
import com.task.data.models.db.Product
import com.task.data.remote.dto.EcommResponse

/**
 *
 */

interface DataSource {
    suspend fun requestCategories(): Resource<EcommResponse>
     fun requestCatgoryFromDataBase(): LiveData<List<Category>>
    fun requestProductsFromDataBase(categoryId:Int): LiveData<List<Product>>
    fun requestProductFromDataBase(productId:Int): LiveData<Product>



}
