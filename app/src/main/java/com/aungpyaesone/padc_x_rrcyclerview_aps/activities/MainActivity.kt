package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.adapter.BaseRecyclerAdapter
import com.aungpyaesone.padc_x_rrcyclerview_aps.adapter.NewsListAdapter
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewpods.EmptyViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),NewsItemDelegate {

    val mNewModel : NewsModel = NewsModelImpl
    private lateinit var mAdapter: NewsListAdapter
    private lateinit var viewPortEmpty: EmptyViewPod

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
        swipeRefreshLayout.isRefreshing = true
        mNewModel.getAllNews(
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
        )
    }

    private fun showEmptyView(){
        /*emptyImage.visibility = View.VISIBLE
        tvEmpty.visibility = View.VISIBLE*/
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView(){
      /*  emptyImage.visibility = View.GONE
        tvEmpty.visibility = View.GONE*/
        vpEmpty.visibility = View.GONE
    }
}
