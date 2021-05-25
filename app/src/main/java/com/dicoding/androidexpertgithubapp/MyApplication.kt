package com.dicoding.androidexpertgithubapp

import android.app.Application
import com.dicoding.androidexpertgithubapp.core.di.databaseModule
import com.dicoding.androidexpertgithubapp.core.di.networkModule
import com.dicoding.androidexpertgithubapp.core.di.repositoryModule
import com.dicoding.androidexpertgithubapp.di.useCaseModule
import com.dicoding.androidexpertgithubapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}