package com.aungpyaesone.padc_x_rrcyclerview_aps.network.responses

import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.CODE_RESPONSE
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.google.gson.annotations.SerializedName

data class GetAllNewsResponse(
    @SerializedName("code")val code:Int =0,
    @SerializedName("message")val message:String ="",
    @SerializedName("data")val data:ArrayList<NewsVO>?=null
){
    fun isResponseOk(): Boolean{
        return data != null && code == CODE_RESPONSE
    }
}