package com.task.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class ApiResponse {

    @SerializedName("categories")
    @Expose
    var categories: List<CategoryResponse>? = null
    @SerializedName("rankings")
    @Expose
    var rankings: List<RankingResponse>? = null

}