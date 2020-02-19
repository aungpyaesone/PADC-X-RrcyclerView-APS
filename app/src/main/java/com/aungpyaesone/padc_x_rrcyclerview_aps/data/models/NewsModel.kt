package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import androidx.lifecycle.LiveData
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import io.reactivex.Observable

interface NewsModel {
   // fun getAllNews():Observable<List<NewsVO>>
    fun getAllNews(onError: (message:String)->Unit): LiveData<List<NewsVO>>

    fun getAllNewsFromApiAndSaveToDatabase(onSuccess:()->Unit, onError: (message: String) -> Unit)
    fun getNewsById(newsId: Int): LiveData<NewsVO>


}