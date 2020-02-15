package com.task.data.localdatabase

import androidx.lifecycle.LiveData
import com.task.data.models.db.Category
import com.task.data.models.db.Product
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

    override  fun getAllCategoriesFromDB(): LiveData<List<Category>> {
        return (appDatabase.categoryDao().getAllCategories())
    }

    override fun getAllProductsFromDB(categoryId: Int): LiveData<List<Product>> {
        return (appDatabase.productDao().getAllProducts(categoryId))
    }

    override fun getProductDetailFromDB(productId: Int): LiveData<Product> {
        return (appDatabase.productDao().getProductDetail(productId))
    }

    override fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): LiveData<List<Product>> {
        return (appDatabase.productDao().getSimilarProductsWithGivenCategoryId(categoryId, productId))
    }

    override suspend fun insertAllProductsInDB(productList: List<Product>) {
        appDatabase.productDao().insertAllProducts(productList)
    }



}