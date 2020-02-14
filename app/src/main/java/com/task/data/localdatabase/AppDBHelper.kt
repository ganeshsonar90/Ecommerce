package com.task.data.localdatabase

import androidx.lifecycle.LiveData
import com.task.data.models.db.*

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDBHelper: DBHelper {

    val appDatabase: AppDatabase

    @Inject
    constructor(database: AppDatabase) {
        this.appDatabase = database
    }

    override suspend fun insertCategoriesInDB(categoryList: List<Category>) {
        appDatabase.categoryDao().insertAllCategories(categoryList)
    }

    override suspend fun getAllCategoriesFromDB(): LiveData<List<Category>> {
        return (appDatabase.categoryDao().getAllCategories())
    }

    override fun getAllProductsFromDB(categoryId: Int): LiveData<List<Product>> {
        return (appDatabase.productDao().getAllProducts(categoryId))
    }

    override fun getProductDetailFromDB(productId: Int): Product {
        return (appDatabase.productDao().getProductDetail(productId))
    }

    override fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): LiveData<List<Product>> {
        return (appDatabase.productDao().getSimilarProductsWithGivenCategoryId(categoryId, productId))
    }

    override suspend fun insertAllProductsInDB(productList: List<Product>) {
        appDatabase.productDao().insertAllProducts(productList)
    }

    override fun insertOrderedRankingInDB(orderedRankingList: List<OrderedRanking>) {
        appDatabase.rankingDao().insertAllOrderedRanking(orderedRankingList)
    }

    override fun insertSharedRankingInDB(sharedRankingList: List<SharedRanking>) {
        appDatabase.rankingDao().insertAllSharedRanking(sharedRankingList)
    }

    override fun insertViewedRankingInDB(viewedRankingList: List<ViewedRanking>) {
        appDatabase.rankingDao().insertAllViewedRanking(viewedRankingList)
    }

    override fun getOrderedRanking(): LiveData<List<OrderedRanking>> {
        return (appDatabase.rankingDao().getOrderedRanking())
    }

    override fun getSharedRanking(): LiveData<List<SharedRanking>> {
        return (appDatabase.rankingDao().getSharedRanking())
    }

    override fun getViewedRanking(): LiveData<List<ViewedRanking>> {
        return (appDatabase.rankingDao().getViewedRanking())
    }

}