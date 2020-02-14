package com.task.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by vikrambhati on 03/12/17.
 */
@Entity(tableName = "shared_ranking")
class SharedRanking {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo
    var shares: Int = 0


}