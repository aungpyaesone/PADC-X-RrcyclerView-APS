package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.DetailView

class NewsDetailImpl : NewsDetailPresenter {


    private var mView: DetailView? = null
    private val mNewsModel: NewsModel = NewsModelImpl

    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {
    mView = null
    }

    override fun initPresenter(view: DetailView) {
      mView = view
    }

    override fun onSwipeRefresh() {

    }
    override fun showEmptyView() {
      mView?.showEmptyView()
    }

    override fun hideEmptyView() {
        mView?.hideEmptyView()
    }

    override fun showError(message: String) {

    }
    override fun onNewsDetailsUiReady(newId: Int) {
        mNewsModel.getNewsById(newId).observeForever {
            mView?.displayNewData(it)
        }
    }

}