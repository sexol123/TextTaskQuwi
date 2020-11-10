package com.example.testtask

import android.app.Application
import android.content.Context
import com.example.testtask.di.networkApiModule
import com.example.testtask.di.repositoriesModule
import com.example.testtask.di.retrofitModule
import com.example.testtask.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)

            // TODO Await fix for Koin and replace the explicit invocations
            //  of loadModules() and createRootScope() with a single call to modules()
            //  (https://github.com/InsertKoinIO/koin/issues/847)
            koin.loadModules(
                listOf(
                    viewModelsModule,
                    repositoriesModule,
                    networkApiModule,
                    retrofitModule
                )
            )
            koin.createRootScope()
           /* modules(
                listOf(
                    viewModelsModule,
                    repositoriesModule,
                    networkApiModule,
                    retrofitModule
                )
            )*/
        }

        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}