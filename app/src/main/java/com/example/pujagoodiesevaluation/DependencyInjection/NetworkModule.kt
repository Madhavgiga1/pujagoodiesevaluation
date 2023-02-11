package com.example.pujagoodiesevaluation.DependencyInjection

import com.example.pujagoodiesevaluation.data.network.YoutubeDataAPI
import com.example.pujagoodiesevaluation.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module//annotation indicates that this class contains one or more methods that provide instances of objects that can be injected into other components.
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    //to make HTTP requests to a server to retrieve data or send data to a server.
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    //to serialize and deserialize JSON data in the application.
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitinstance(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit):YoutubeDataAPI{
        return retrofit.create(YoutubeDataAPI::class.java)

    }

}