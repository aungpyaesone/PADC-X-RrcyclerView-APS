package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

interface NewsModel {
    fun getAllNews(onSuccess : (List<NewsVO>)-> Unit,
                   onFailure : (String) -> Unit)
    fun getNewsById(newsId: Int): NewsVO
}