package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.adapter.BaseRecyclerAdapter
import com.aungpyaesone.padc_x_rrcyclerview_aps.adapter.NewsListAdapter
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters.MainPresenter
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters.MainPresenterImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.MainView
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewpods.EmptyViewPod
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainView {

   // private val mNewModel : NewsModel = NewsModelImpl
    private lateinit var mAdapter: NewsListAdapter
    private lateinit var viewPortEmpty: EmptyViewPod
    private val compositeDisposable = CompositeDisposable()

    private lateinit var mPresenter: MainPresenter

  /*  override fun onTouchNewsItem(id:Int) {
        startActivity(DetailActivity.newIntent(this,id))
    }

    override fun touchLike() {
       // Snackbar.make(coordinator_layout,"Hi there! ",Snackbar.LENGTH_SHORT).show()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
        setUpSwipeRefresh()
        setUpRecyclerView()
        setUpViewPod()
        mPresenter.onCreate()

    }

    private fun setUpPresenter(){
        mPresenter = MainPresenterImpl()
        mPresenter.initPresenter(this)
    }

    // main view override method
    override fun displayNewsList(newsList: List<NewsVO>) {
        mAdapter.setData(newsList.toMutableList())
    }

    override fun enableSwipeRefresh() {
       swipeRefreshLayout.isRefreshing = true
    }

    override fun disableSwipeRefresh() {
       swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmptyView() {
       vpEmpty.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }

    override fun showError(message: String) {
       showSnackBar(message)
    }

    override fun navigateToNewsDetails(newsId: Int) {
      startActivity(DetailActivity.newIntent(this,newsId))
    }

    private fun setUpViewPod(){
        viewPortEmpty = vpEmpty as EmptyViewPod
        viewPortEmpty.setEmptyData("Hello World","https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png")
    }

    private fun setUpSwipeRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.onSwipeRefresh()
        }
    }

    private fun setUpRecyclerView(){
        mAdapter = NewsListAdapter(mPresenter)
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val gridLayoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = mAdapter

    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
        compositeDisposable.dispose()
    }
}
