package com.aungpyaesone.padc_x_rrcyclerview_aps.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl

class GetNewsWorker(context: Context, workerParameter:WorkerParameters)
    : BaseWorker(context,workerParameter){
    override fun doWork(): Result {
        var result = Result.failure()

        mNewsModelImpl.getAllNewsFromApiAndSaveToDatabase(
            onSuccess = {
                result = Result.success()
            },
            onError = {
                result = Result.failure()
            }
        )
    return result
    }

}