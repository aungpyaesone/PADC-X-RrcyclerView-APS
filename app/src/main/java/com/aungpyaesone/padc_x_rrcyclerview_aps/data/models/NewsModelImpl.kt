package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters.db.NewsDB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object NewsModelImpl: NewsModel, BaseModel() {

    @SuppressLint("CheckResult")
    override fun getAllNewsFromApiAndSaveToDatabase(
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ) {
        mNewApi.getAllNews(ACCESS_TOKEN)
            .map { it.data?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                mTheDB.newsDao().insertAllNews(it)
            },{
                onError(it.localizedMessage ?: EN_CONNECTION_ERROR)
            })
    }

    val mNewsRepository: HashMap<Int,NewsVO> = hashMapOf()
   // private val mTheDB: NewsDB = NewsDB.getInstance(context = context)

    override fun getAllNews(onError: (message: String) -> Unit): LiveData<List<NewsVO>> {

      /*  mNewApi.getAllNews(ACCESS_TOKEN)
            .map { it.data?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mTheDB.newsDao().insertAllNews(it)
            },{
                onError(it.localizedMessage ?: EN_CONNECTION_ERROR)
            }
            )*/
        return mTheDB.newsDao().getAllNews()
    }

   /* override fun getAllNews() : Observable<List<NewsVO>>{

       return mNewApi.getAllNews(ACCESS_TOKEN)
           .map{ it.data?.toList() ?: listOf() }
           .subscribeOn(Schedulers.io())

    }
*/
    override fun getNewsById(newsId: Int):LiveData<NewsVO> {
      /*  mNewsRepository[newsId]?.let {
            return it
        }
        return NewsVO()*/
       return mTheDB.newsDao().getNewsById(newsId)


    }
}