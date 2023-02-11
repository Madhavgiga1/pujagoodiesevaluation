package com.example.pujagoodiesevaluation.data

import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.data.network.YoutubeDataAPI
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI){

    suspend fun getChannel(queries:Map<String, String>): Response<ChannelVideoList>{
        return youtubeDataAPI.getChannel(queries)
    }
}