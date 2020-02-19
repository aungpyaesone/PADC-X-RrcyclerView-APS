package com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsVO>>

    @Query ("select * from news where id = :noteId")
    fun getNewsById (noteId: Int) : LiveData<NewsVO>

    @Query("delete from news")
    fun deleteAll()

    @Delete
    fun deleteNews(note: NewsVO)

    @Insert
    fun insertNews(noteVo: NewsVO)

    @Insert
    fun insertAllNews(news: List<NewsVO>)

}