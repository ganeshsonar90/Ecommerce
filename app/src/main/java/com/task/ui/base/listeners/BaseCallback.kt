package com.task.ui.base.listeners

import com.task.data.error.Error

/**
 *
 */

interface BaseCallback {
    fun onSuccess(data: Any)

    fun onFail(error : Error)
}
