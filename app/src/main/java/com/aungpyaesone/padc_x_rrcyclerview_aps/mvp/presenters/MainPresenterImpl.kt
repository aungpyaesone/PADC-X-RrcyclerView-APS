package com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters

import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.MainView

class MainPresenterImpl : MainPresenter {

    // connect to main activity

    // getting reference
    private var mView: MainView? = null
    private val mNewsModel: NewsModel = NewsModelImpl

   override fun initPresenter(view: MainView){
        mView = view
    }

    override fun onTouchNewsItem(id: Int) {
        mView?.navigateToNewsDetails(id)
    }

    override fun touchLike() {

           }

    override fun onCreate() {
        mView?.hideEmptyView()
        getNewsData()
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

    override fun onSwipeRefresh() {
      getNewsData()
    }

    private fun getNewsData(){
        mView?.hideEmptyView()
        mView?.enableSwipeRefresh()

        mNewsModel.getAllNews(onError = {
            mView?.disableSwipeRefresh()
            mView?.showEmptyView()
            mView?.showError(it)
        }).observeForever {
            mView?.disableSwipeRefresh()
            if(it.isEmpty())
            {
                mView?.showEmptyView()
            }else{
                mView?.hideEmptyView()
                mView?.displayNewsList(it)
            }
        }
    }
}