package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModel
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.models.NewsModelImpl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {
    private val mNewsModel: NewsModel = NewsModelImpl


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
       // showAlertDialog("Hello BaseActivity, I am main activity")
        var newsId = intent.getIntExtra(NEWS_ID,0)
        val newsVo = mNewsModel.getNewsById(newsId)

        Glide.with(this)
            .load(newsVo.heroImage)
            .into(imgDetail)

        tv_title.text = newsVo.title
        date.text = newsVo.publication?.postedDate
        description.text = newsVo.description

    }
}
