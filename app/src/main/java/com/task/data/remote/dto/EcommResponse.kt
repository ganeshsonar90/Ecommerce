package com.task.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class EcommResponse {

    @SerializedName("categories")
    @Expose
    var categoryRemotes: List<CategoryRemote>? = null
    @SerializedName("rankings")
    @Expose
    var rankings: List<RankingResponse>? = null

}