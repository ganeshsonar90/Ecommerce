/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.task.di
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.data.localdatabase.AppDBHelper
import com.task.data.localdatabase.AppDatabase
import com.task.data.localdatabase.DBHelper
import com.task.utils.Constants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDBHelper): DBHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @DataInfo
    fun provideDatabaseName(): String {
        return Constants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DataInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }
}
