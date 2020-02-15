package com.task.utils


import androidx.test.espresso.IdlingResource


/**
 * Contains a static reference to [IdlingResource], only available in the 'mock' build type.
 */
class EspressoIdlingResource {
    companion object INSTANCE {
        private const val RESOURCE = "GLOBAL"

        private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

        val idlingResource: IdlingResource
            get() = mCountingIdlingResource

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }
    }
}