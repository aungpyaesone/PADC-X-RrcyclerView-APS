package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

interface MainView {

    // actions function

    fun displayNewsList(newsList:List<NewsVO>)
    fun enableSwipeRefresh()
    fun disableSwipeRefresh()

    fun showEmptyView()
    fun hideEmptyView()
    fun showError(message: String)

    fun navigateToNewsDetails(newsId:Int)


}