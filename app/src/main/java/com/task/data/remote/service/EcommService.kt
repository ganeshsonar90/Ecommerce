package com.task.data.remote.service

import com.task.data.remote.dto.EcommResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 */

interface EcommService {
    @GET("/json")
    suspend fun fetchEcommCat(): Response<EcommResponse>
}
