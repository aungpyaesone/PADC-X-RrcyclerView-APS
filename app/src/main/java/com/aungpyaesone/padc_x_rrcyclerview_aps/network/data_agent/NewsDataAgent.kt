package com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

interface NewsDataAgent {
    fun getAllNews(accessToken: String,
                   onSuccess: (List<NewsVO>) ->Unit,
                   onFailure: (String)-> Unit)
}