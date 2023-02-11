package com.example.pujagoodiesevaluation.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.utils.Constants.Companion.CHANNELS_TABLE

@Entity(tableName=CHANNELS_TABLE)
class ChannelEntity(var channelVideoList: ChannelVideoList) {
    @PrimaryKey(autoGenerate =true)
    var id:Int = 0
}