package com.example.pujagoodiesevaluation.models.mostwatched


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ChannelVideoList(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: @RawValue List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: @RawValue PageInfo,
    @SerializedName("regionCode")
    val regionCode: String
):Parcelable