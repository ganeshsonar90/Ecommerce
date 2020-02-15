package com.task.data.localdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.data.models.db.Product


@Dao
interface ProductDAO {

    @Query("SELECT * FROM product WHERE categoryId = :categoryId")
    fun getAllProducts(categoryId: Int): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE productId = :productId")
    fun getProductDetail(productId: Int):LiveData<Product>

    @Query("SELECT * FROM product WHERE categoryId = :categoryId AND NOT productId = :productId")
    fun getSimilarProductsWithGivenCategoryId(categoryId: Int, productId: Int): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAllProducts(productList: List<Product>): Unit
}