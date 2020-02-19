package com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters.daos.NewsDao

@Database (entities = [NewsVO::class], version = 2,exportSchema = false)
abstract class NewsDB: RoomDatabase() {

    companion object{
        val DB_NAME = "PADC_NEWS_X_DB"
        var dbInstance: NewsDB? = null

        fun getInstance(context: Context): NewsDB{
            when(dbInstance){
                null ->{
                    dbInstance = Room.databaseBuilder(context,NewsDB::class.java,
                        DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }

    abstract fun newsDao(): NewsDao
}