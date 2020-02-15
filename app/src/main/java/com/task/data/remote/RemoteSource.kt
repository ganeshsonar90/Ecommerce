package com.task.data.remote

import com.task.data.Resource
import com.task.data.remote.dto.EcommResponse

/**
 *
 */

internal interface RemoteSource {
    suspend fun requestNews(): Resource<EcommResponse>
}
