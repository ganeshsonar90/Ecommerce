package com.task.data.localdatabase.converters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.data.models.TaxInfo

/**
 */
class TaxInfoConverter {

    @TypeConverter
    fun fromString(value: String): TaxInfo {
        val listType = object : TypeToken<TaxInfo>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTaxInfo(taxInfo: TaxInfo): String {
        val gson = Gson()
        val json = gson.toJson(taxInfo)
        return json
    }

}