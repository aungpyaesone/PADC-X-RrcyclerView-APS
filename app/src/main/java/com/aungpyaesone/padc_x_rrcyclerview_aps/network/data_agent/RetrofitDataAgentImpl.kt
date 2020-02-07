package com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent

import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.BASE_URL
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.NewsApi
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.responses.GetAllNewsResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : NewsDataAgent {
    private var mNewApi: NewsApi? = null
    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

       mNewApi = retrofit.create(NewsApi::class.java)
    }
    override fun getAllNews(
        accessToken: String,
        onSuccess: (List<NewsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val getAllNewsCall = mNewApi?.getAllNews(accessToken)
        getAllNewsCall?.enqueue(object : Callback<GetAllNewsResponse> {
            override fun onFailure(call: Call<GetAllNewsResponse>, t: Throwable) {
               onFailure(t.message ?: EN_CONNECTION_ERROR)
            }

            override fun onResponse(
                call: Call<GetAllNewsResponse>,
                response: Response<GetAllNewsResponse>
            ) {
               val getAllNewsResponse = response.body()
                if(getAllNewsResponse !=null)
                {
                    if(getAllNewsResponse.isResponseOk())
                    {
                        getAllNewsResponse.data?.let {
                            onSuccess(it)
                        }
                    }
                    else{
                        onFailure(getAllNewsResponse.message)
                    }
                }
                else{
                    onFailure(EN_CONNECTION_ERROR)
                }
            }
        })
    }

}