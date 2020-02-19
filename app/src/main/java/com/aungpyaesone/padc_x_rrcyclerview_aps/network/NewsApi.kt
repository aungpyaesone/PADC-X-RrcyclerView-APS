package com.aungpyaesone.padc_x_rrcyclerview_aps.network

import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.END_POINT
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.responses.GetAllNewsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewsApi {

    @FormUrlEncoded
    @POST(END_POINT)
    fun getAllNews(@Field(ACCESS_TOKEN) accessToken: String)
            : Observable<GetAllNewsResponse>
}