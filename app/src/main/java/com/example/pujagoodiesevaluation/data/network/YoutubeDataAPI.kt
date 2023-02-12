package com.example.pujagoodiesevaluation.data.network

import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface YoutubeDataAPI {
    @GET("search")
    suspend fun getChannel(@QueryMap queries:Map<String, String>): Response<ChannelVideoList>





    /*@GET()
    suspend fun  getLiveVideo(@QueryMap queries: Map<String, String>): Response<>*/
}