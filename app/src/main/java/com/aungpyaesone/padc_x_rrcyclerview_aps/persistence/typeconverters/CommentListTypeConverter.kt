package com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters

import androidx.room.TypeConverter
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.CommentVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CommentListTypeConverter  {
    @TypeConverter
    fun toString(commentList : ArrayList<CommentVO>): String{
        return Gson().toJson(commentList)
    }

    @TypeConverter
    fun toCommentList(CommentListJsonStr: String): ArrayList<CommentVO>{
        val commentListType = object : TypeToken<ArrayList<CommentVO>>() {}.type
        return Gson().fromJson(CommentListJsonStr,commentListType)
    }
}