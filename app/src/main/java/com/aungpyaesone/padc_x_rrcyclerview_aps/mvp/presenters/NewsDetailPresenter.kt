package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters

import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.DetailView
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.MainView

interface NewsDetailPresenter{

    fun onCreate()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()

    fun showEmptyView()
    fun hideEmptyView()
    fun showError(message: String)

    fun onNewsDetailsUiReady(newId: Int)

    fun initPresenter(view: DetailView)
    fun onSwipeRefresh()
}