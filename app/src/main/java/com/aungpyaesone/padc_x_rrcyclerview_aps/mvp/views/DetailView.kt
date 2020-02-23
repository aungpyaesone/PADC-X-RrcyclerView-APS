package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

interface DetailView {
    fun showEmptyView()
    fun hideEmptyView()

    fun displayNewData(data: NewsVO)
}