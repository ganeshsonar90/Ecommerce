package com.task.data.localdatabase.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.data.models.db.OrderedRanking
import com.task.data.models.db.SharedRanking
import com.task.data.models.db.ViewedRanking


@Dao
interface RankingDAO {

    @Query("SELECT * FROM ordered_ranking ORDER BY order_count DESC")
    fun getOrderedRanking(): LiveData<List<OrderedRanking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrderedRanking(orderedRanking: List<OrderedRanking>) : Unit

    @Query("SELECT * FROM shared_ranking ORDER BY shares DESC ")
    fun getSharedRanking(): LiveData<List<SharedRanking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSharedRanking(sharedRanking: List<SharedRanking>) : Unit

    @Query("SELECT * FROM viewed_ranking ORDER BY view_count DESC")
    fun getViewedRanking(): LiveData<List<ViewedRanking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllViewedRanking(viewedRanking: List<ViewedRanking>) : Unit

}