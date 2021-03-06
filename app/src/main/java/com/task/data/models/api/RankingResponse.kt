package com.task.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *
 */
class RankingResponse {

    @SerializedName("ranking")
    @Expose
    var ranking: String? = null
    @SerializedName("products")
    @Expose
    var products: List<ViewCountResponse>? = null

}