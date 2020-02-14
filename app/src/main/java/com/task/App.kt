package com.task

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.task.di.AppComponent
import com.task.di.DaggerAppComponent

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

open class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent : AppComponent


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    open fun initDagger() {
        //DaggerAppComponent.builder().build().inject(this)
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
       // DaggerApp
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        lateinit var context: Context

    }
}
