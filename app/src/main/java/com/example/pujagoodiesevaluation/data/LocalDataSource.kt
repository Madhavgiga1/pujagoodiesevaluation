package com.example.pujagoodiesevaluation.data

import com.example.pujagoodiesevaluation.data.database.ChannelEntity
import com.example.pujagoodiesevaluation.data.database.ChannelsDAO
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val channelsDAO: ChannelsDAO) {

    fun readDatabase(): Flow<List<ChannelEntity>>{
        return channelsDAO.readChannel()
    }
    suspend fun insertChannels(channelEntity: ChannelEntity){
        channelsDAO.insertChannel(channelEntity)
    }
}