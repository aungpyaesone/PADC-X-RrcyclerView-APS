package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

object NewsModelImpl: NewsModel, BaseModel() {

    val mNewsRepository: HashMap<Int,NewsVO> = hashMapOf()


    override fun getAllNews(
        onSuccess: (List<NewsVO>) -> Unit,
        onFailure: (String) -> Unit) {

        mDataAgent.getAllNews(ACCESS_TOKEN,
            onSuccess = {

                it.forEach {news->
                    mNewsRepository[news.id] = news
                }
                onSuccess.invoke(arrayListOf())
            },
            onFailure = {
                onFailure.invoke(it)
            })
    }

    override fun getNewsById(newsId: Int):NewsVO {
        mNewsRepository[newsId]?.let {
            return it
        }
        return NewsVO()
    }
}