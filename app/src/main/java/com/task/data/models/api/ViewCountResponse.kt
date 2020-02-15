package com.task.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *
 */
class ViewCountResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("view_count")
    @Expose
    var viewCount: Int? = null
    @SerializedName("order_count")
    @Expose
    var orderCount: Int? = null
    @SerializedName("shares")
    @Expose
    var shares: Int? = null

}