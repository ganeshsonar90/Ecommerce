package com.task.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *    on 24/11/17.
 */
class TaxResponse {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: Float? = null

}
