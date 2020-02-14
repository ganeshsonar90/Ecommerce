package com.task.data.localdatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.data.localdatabase.converters.TaxInfoConverter
import com.task.data.localdatabase.converters.VariantConverter
import com.task.data.localdatabase.db.CategoryDAO
import com.task.data.localdatabase.db.ProductDAO
import com.task.data.localdatabase.db.RankingDAO
import com.task.data.models.db.*

/**
 * Created by vikrambhati on 23/11/17.
 */
@Database(entities = arrayOf(Category::class, Product::class, OrderedRanking::class,
        SharedRanking::class, ViewedRanking::class), version = 1)
@TypeConverters(TaxInfoConverter::class, VariantConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDAO

    abstract fun productDao(): ProductDAO

    abstract fun rankingDao() : RankingDAO

}