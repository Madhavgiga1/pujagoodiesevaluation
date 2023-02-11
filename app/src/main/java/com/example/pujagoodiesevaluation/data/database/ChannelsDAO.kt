package com.example.pujagoodiesevaluation.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import kotlinx.coroutines.flow.Flow


@Dao
interface ChannelsDAO {
    //ChannelEntity object holds a reference to a ChannelVideoList object.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannel(channelEntity: ChannelEntity)

    @Query("SELECT * FROM channels_table ORDER BY id ASC")
    fun readChannel(): Flow<List<ChannelEntity>>
}