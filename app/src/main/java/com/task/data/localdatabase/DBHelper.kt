package com.task.data.localdatabase

import androidx.lifecycle.LiveData
import com.task.data.models.db.*


interface DBHelper {

  suspend  fun insertCategoriesInDB(categoryList: List<Category>): Unit

   suspend fun getAllCategoriesFromDB(): LiveData<List<Category>>

    fun getAllProductsFromDB(categoryId: Int): LiveData<List<Product>>

    fun getProductDetailFromDB(productId: Int): Product

    fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): LiveData<List<Product>>

   suspend fun insertAllProductsInDB(productList: List<Product>): Unit

    fun insertOrderedRankingInDB(orderedRankingList: List<OrderedRanking>): Unit

    fun insertSharedRankingInDB(sharedRankingList: List<SharedRanking>): Unit

    fun insertViewedRankingInDB(viewedRankingList: List<ViewedRanking>): Unit

    fun getOrderedRanking(): LiveData<List<OrderedRanking>>

    fun getSharedRanking(): LiveData<List<SharedRanking>>

    fun getViewedRanking(): LiveData<List<ViewedRanking>>

}