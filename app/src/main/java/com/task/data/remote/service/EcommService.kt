package com.task.data.remote.service

import com.task.data.remote.dto.EcommResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by AhmedEltaher on 5/12/2016
 */

interface EcommService {
    @GET("/json")
    suspend fun fetchEcommCat(): Response<EcommResponse>
}
