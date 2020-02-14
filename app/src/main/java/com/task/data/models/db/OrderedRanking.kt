package com.task.data.models.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by vikrambhati on 03/12/17.
 */
@Entity(tableName = "ordered_ranking")
class OrderedRanking {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo
    var order_count: Int = 0

}