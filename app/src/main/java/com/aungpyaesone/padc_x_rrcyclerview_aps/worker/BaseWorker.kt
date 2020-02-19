package com.aungpyaesone.padc_x_rrcyclerview_aps.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl

abstract class BaseWorker(context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters){
    val mNewsModelImpl: NewsModel = NewsModelImpl
}