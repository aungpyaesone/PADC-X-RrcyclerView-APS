package com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent

import android.os.AsyncTask
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.BASE_URL
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.END_POINT
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.responses.GetAllNewsResponse
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Timeout
import java.lang.Exception
import java.util.concurrent.TimeUnit

object OKHttpUrlConnectionDataAgentImpl : NewsDataAgent {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15,TimeUnit.SECONDS)
        .readTimeout(15,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .build()
    override fun getAllNews(
        accessToken: String,
        onSuccess: (List<NewsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNewTask(mOkHttpClient = okHttpClient,
                    accessToken = accessToken,
            onSuccess = onSuccess,
            onFailure = onFailure).execute()
    }

    class GetNewTask(
        private val mOkHttpClient: OkHttpClient,
        private val accessToken: String,
        private val onSuccess: (List<NewsVO>) -> Unit,
        private val onFailure: (String) -> Unit): AsyncTask<Void, Void, GetAllNewsResponse>() {

        override fun doInBackground(vararg params: Void?): GetAllNewsResponse? {
        val formBody = FormBody.Builder()
            .add(ACCESS_TOKEN,accessToken)
            .build()

            val request = Request.Builder()
                .url(BASE_URL + END_POINT)
                .post(formBody)
                .build()

            try {
                val response = mOkHttpClient
                    .newCall(request)
                    .execute()
                if(response.isSuccessful)
                {
                    response.body?.let {
                        val responseString = it.string()
                        return Gson().fromJson<GetAllNewsResponse>(
                            responseString,
                            GetAllNewsResponse::class.java
                        )
                    }
                }
            }catch (e:Exception)
            {

            }
            return null
        }

        override fun onPostExecute(result: GetAllNewsResponse?) {
            super.onPostExecute(result)
            if(result !=null){
                if(result.isResponseOk()){
                    result.data?.let {
                        onSuccess(it.toList())
                    }
                }else{
                    onFailure(result.message)
                }
            }
            else{
                onFailure(EN_CONNECTION_ERROR)
            }
        }
    }

}