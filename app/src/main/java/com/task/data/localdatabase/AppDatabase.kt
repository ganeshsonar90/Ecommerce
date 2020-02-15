package com.task.data.localdatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.data.localdatabase.converters.TaxInfoConverter
import com.task.data.localdatabase.converters.VariantConverter
import com.task.data.localdatabase.db.CategoryBaseDAO
import com.task.data.localdatabase.db.ProductDAO
import com.task.data.models.db.Category
import com.task.data.models.db.Product

/**
 *
 */
@Database(entities = arrayOf(Category::class, Product::class), version = 1)
@TypeConverters(TaxInfoConverter::class, VariantConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryBaseDAO

    abstract fun productDao(): ProductDAO


}