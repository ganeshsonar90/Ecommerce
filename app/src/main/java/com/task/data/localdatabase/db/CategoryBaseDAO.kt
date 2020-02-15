package com.task.data.localdatabase.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.data.models.db.Category


@Dao
interface CategoryBaseDAO {

    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAllCategories(categoryList: List<Category>) : Unit

}