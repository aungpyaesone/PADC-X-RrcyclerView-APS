package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import android.content.Context
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.BASE_URL
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.NewsApi
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.HttUrlConnectionDataAgentImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.NewsDataAgent
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.OKHttpUrlConnectionDataAgentImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.RetrofitDataAgentImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters.db.NewsDB
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

   protected var mNewApi: NewsApi
   protected lateinit var mTheDB: NewsDB

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mOkHttpClient)
            .build()

      mNewApi = retrofit.create(NewsApi::class.java)
    }

    fun initDatabase(context: Context){
        mTheDB = NewsDB.getInstance(context)
    }
}