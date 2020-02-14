package com.task.data.remote

import com.task.data.Resource
import com.task.data.remote.dto.EcommResponse

/**
 * Created by ahmedEltaher on 3/23/17.
 */

internal interface RemoteSource {
    suspend fun requestNews(): Resource<EcommResponse>
}
