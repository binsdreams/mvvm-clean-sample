package com.bins

import android.app.Application
import com.bins.presentation.di.mLocalModules
import com.bins.presentation.di.mNetworkModules
import com.bins.presentation.di.mRepositoryModules
import com.bins.presentation.di.mUseCaseModules
import org.koin.android.ext.android.startKoin

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(
                mNetworkModules,
                mRepositoryModules,
                mUseCaseModules,
                mLocalModules)

        )
    }
}