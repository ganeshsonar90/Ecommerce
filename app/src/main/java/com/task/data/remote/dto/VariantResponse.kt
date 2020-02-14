package com.task.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class VariantResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("size")
    @Expose
    var size: String? = null
    @SerializedName("price")
    @Expose
    var price: Int? = null

}