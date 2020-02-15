package com.task.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *
 */
class CategoryResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("products")
    @Expose
    var products: List<ProductResponse>? = null
    @SerializedName("child_categories")
    @Expose
    var childCategories: List<Int>? = null

}