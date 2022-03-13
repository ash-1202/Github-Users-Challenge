package com.jodel.githubusers

import android.app.Application
import com.jodel.githubusers.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import com.jodel.githubusers.BuildConfig

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(
                usersListViewModel,
                githubApiModule,
                githubApiClientModule,
                usersListDataSourceFactory,
                singleUserViewModel
            ))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}