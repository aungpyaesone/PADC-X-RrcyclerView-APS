package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters.NewsDetailImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.presenters.NewsDetailPresenter
import com.aungpyaesone.padc_x_rrcyclerview_aps.mvp.views.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailView {


 //   private val mNewsModel: NewsModel = NewsModelImpl

    private lateinit var mDetailPresenter: NewsDetailPresenter


    companion object{
        const val NEWS_ID = "newsId"

        fun newIntent(context: Context,id:Int):Intent{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(NEWS_ID,id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setUpPresenter()
        var newsId = intent.getIntExtra(NEWS_ID,0)
        mDetailPresenter.getNewsById(newsId)
        mDetailPresenter.onCreate()

    }

    override fun showEmptyView() {
        mDetailPresenter.showEmptyView()
    }

    override fun hideEmptyView() {
        mDetailPresenter.hideEmptyView()
    }

    override fun displayNewData(data: NewsVO) {
        Glide.with(this)
            .load(data.heroImage)
            .into(imgDetail)

        tv_title.text = data.title
        date.text = data.publication?.postedDate
        description.text = data.description

        data.comments.forEach {
            Log.e("ccc", it.commentMessage)
        }
    }

    override fun onStart() {
        super.onStart()
        mDetailPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mDetailPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        mDetailPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        mDetailPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailPresenter.onDestroy()
    }

    private fun setUpPresenter(){
        mDetailPresenter = NewsDetailImpl()
        mDetailPresenter.initPresenter(this)

    }
}
