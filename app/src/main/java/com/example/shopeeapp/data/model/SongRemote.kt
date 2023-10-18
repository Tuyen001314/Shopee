package com.example.shopeeapp.data.model

import com.google.gson.annotations.SerializedName

class SongRemote {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("category")
    var category: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String = ""
    @SerializedName("creator")
    var creator: Int = 0
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("downloadCount")
    var downloadCount: Int = 0
    @SerializedName("listenedCount")
    var listenedCount: Int = 0
}