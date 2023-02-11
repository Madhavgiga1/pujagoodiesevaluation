package com.example.pujagoodiesevaluation.DependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.pujagoodiesevaluation.data.database.ChannelsDatabase
import com.example.pujagoodiesevaluation.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)//It indicates that a single instance of this class should be shared throughout the entire application.
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(context,ChannelsDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: ChannelsDatabase)=database.channelDao()
}