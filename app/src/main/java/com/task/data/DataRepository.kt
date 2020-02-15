package com.task.data


import androidx.lifecycle.LiveData
import com.task.data.localdatabase.AppDBHelper
import com.task.data.models.db.Category
import com.task.data.models.db.Product
import com.task.data.remote.RemoteRepository
import com.task.data.remote.dto.EcommResponse
import javax.inject.Inject




class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository, private val localRepository: AppDBHelper) : DataSource {

    override suspend fun requestCategories(): Resource<EcommResponse> {
        return remoteRepository.requestNews()
    }

    override  fun requestCatgoryFromDataBase(): LiveData<List<Category>> {
       return localRepository.getAllCategoriesFromDB()
    }

    override fun requestProductsFromDataBase(categoryId: Int): LiveData<List<Product>> {
        return localRepository.getAllProductsFromDB(categoryId)
    }

    override fun requestProductFromDataBase(productId: Int): LiveData<Product> {
        return localRepository.getProductDetailFromDB(productId)
    }


}
