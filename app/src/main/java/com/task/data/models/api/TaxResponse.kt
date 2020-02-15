package com.task.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *
 */
class TaxResponse {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: Float? = null

}
