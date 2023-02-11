package com.example.pujagoodiesevaluation.data.database

import androidx.room.TypeConverter
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChannelTypeConverter {

    var gson=Gson()

    @TypeConverter
    fun channeltoString(channelVideoList: ChannelVideoList):String{
        return gson.toJson(channelVideoList)
    }

    @TypeConverter
    fun stringtoChannel(data:String):ChannelVideoList{
        val listType=object : TypeToken<ChannelVideoList>() {}.type
        return gson.fromJson(data,listType)
    }

}