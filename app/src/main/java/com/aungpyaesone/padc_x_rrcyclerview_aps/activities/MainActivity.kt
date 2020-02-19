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
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewpods.EmptyViewPod
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),NewsItemDelegate {

    private val mNewModel : NewsModel = NewsModelImpl
    private lateinit var mAdapter: NewsListAdapter
    private lateinit var viewPortEmpty: EmptyViewPod
    private val compositeDisposable = CompositeDisposable()

    override fun onTouchNewsItem(id:Int) {
        startActivity(DetailActivity.newIntent(this,id))
    }

    override fun touchLike() {
       // Snackbar.make(coordinator_layout,"Hi there! ",Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideEmptyView()
        setUpSwipeRefresh()
        setUpRecyclerView()
        requestData()
        setUpViewPod()

    }

    private fun setUpViewPod(){
        viewPortEmpty = vpEmpty as EmptyViewPod
        viewPortEmpty.setEmptyData("Hello World","https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png")
    }

    private fun setUpSwipeRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            requestData()
        }
    }

    private fun setUpRecyclerView(){
        mAdapter = NewsListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val gridLayoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = mAdapter

    }

   private fun requestData(){
      //  mNewModel = NewsModelImpl
        swipeRefreshLayout.isRefreshing = true

        mNewModel.getAllNews(onError = {
           swipeRefreshLayout.isRefreshing = false
           showEmptyView()
           Log.e("error",it)
       }).observe(this, Observer {
           swipeRefreshLayout.isRefreshing = false
           if(it.isNotEmpty()){
               hideEmptyView()
               mAdapter.setData(it.toMutableList())
           }

       })

       // simple callback

       /* mNewModel.getAllNews(
            onSuccess = {
                // Bind Data with Recycler View
           //     val newList = it
                if(it.isNotEmpty()){

                   mAdapter.setData(it.toMutableList())
                }
                else{
                    showEmptyView()
                }

                swipeRefreshLayout.isRefreshing = false

            },
            onFailure = {
                // Show Error Message
           //     val errorMessage = it
                showSnackBar(it)
                swipeRefreshLayout.isRefreshing = false
                showEmptyView()
            }
        )*/


       // with Rx
     /*  mNewModel.getAllNews()
          // .subscribeOn(Schedulers.io())// it is upstream for running on background thread
           .observeOn(AndroidSchedulers.mainThread()) // it is for running on main thread ( downstream )
           .subscribe(
           {
               swipeRefreshLayout.isRefreshing = true
               if(it.isNotEmpty()){
                   mAdapter.setData(it.toMutableList())
               }
               else{
                   showEmptyView()
               }
           },
           {
               showSnackBar(it.message.toString())
               swipeRefreshLayout.isRefreshing = false
               showEmptyView()
           }

       ).addTo(compositeDisposable)*/
    }

    private fun showEmptyView(){
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView(){
        vpEmpty.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
