package com.aungpyaesone.padc_x_rrcyclerview_aps.persistence.typeconverters

import androidx.room.TypeConverter
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.UserVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserLikeTypeConverter {
    @TypeConverter
    fun toString(userList: ArrayList<UserVO>): String{
        return Gson().toJson(userList)
    }

    @TypeConverter
    fun toUserList(userListJsonString : String) : ArrayList<UserVO>{
        val userListtype = object : TypeToken<ArrayList<UserVO>>() {}.type
        return Gson().fromJson(userListJsonString,userListtype)
    }
}