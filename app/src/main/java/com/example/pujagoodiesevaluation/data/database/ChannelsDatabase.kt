package com.example.pujagoodiesevaluation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [ChannelEntity::class], version = 1, exportSchema = false
)
@TypeConverters(ChannelTypeConverter::class)
abstract class ChannelsDatabase:RoomDatabase() {
    abstract fun channelDao():ChannelsDAO
}