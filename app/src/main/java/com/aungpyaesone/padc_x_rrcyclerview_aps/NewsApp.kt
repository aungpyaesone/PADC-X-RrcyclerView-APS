package com.aungpyaesone.padc_x_rrcyclerview_aps

import android.app.Application
import androidx.work.*
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.worker.GetNewsWorker
import java.util.concurrent.TimeUnit

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsModelImpl.initDatabase(applicationContext)
        getNewsOneTime()

    }

    // getting worker request in work manager
    // getNew one time call
    private fun getNewsOneTime(){
        val getEventWorkRequest = OneTimeWorkRequest
            .Builder(GetNewsWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(getEventWorkRequest)
    }

/// periodically work request in work manager
    private fun getNewsPeroidically(){
        val constraint = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventPeriodicallyWorkRequest = PeriodicWorkRequest
            .Builder(GetNewsWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventPeriodicallyWorkRequest)
    }

    //
    private fun getNewsWhileInDozeMode(){
        val constraint = Constraints
            .Builder()
            .setRequiresDeviceIdle(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventWhileDozeModeWorkRequest = PeriodicWorkRequest
            .Builder(GetNewsWorker::class.java,1,TimeUnit.HOURS)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventWhileDozeModeWorkRequest)
    }
}