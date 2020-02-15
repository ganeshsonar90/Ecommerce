package com.task.data.localdatabase.converters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.data.models.VariantInfo
import java.util.*

/**
 *
 */
class VariantConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<VariantInfo> {
        val listType = object : TypeToken<ArrayList<VariantInfo>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromVariantInfo(list: ArrayList<VariantInfo>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }

}