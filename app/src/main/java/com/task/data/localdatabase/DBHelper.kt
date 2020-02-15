package com.task.data.localdatabase

import androidx.lifecycle.LiveData
import com.task.data.models.db.Category
import com.task.data.models.db.Product


interface DBHelper {

  suspend  fun insertCategoriesInDB(categoryList: List<Category>): Unit

    fun getAllCategoriesFromDB(): LiveData<List<Category>>

    fun getAllProductsFromDB(categoryId: Int): LiveData<List<Product>>

    fun getProductDetailFromDB(productId: Int): LiveData<Product>

    fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): LiveData<List<Product>>

   suspend fun insertAllProductsInDB(productList: List<Product>): Unit


}