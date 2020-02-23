package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters

import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.MainView

interface MainPresenter : NewsItemDelegate{
    fun onCreate()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()

    fun initPresenter(view: MainView)
    fun onSwipeRefresh()

}