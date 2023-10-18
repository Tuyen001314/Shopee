package com.example.shopeeapp.data.model

data class Data(
    val avatarUrl: Any,
    val createdAt: String,
    val followers: List<Any>,
    val followersCount: Int,
    val iaAdmin: Boolean,
    val id: Int,
    val listenedHistories: List<Any>,
    val name: String,
    val password: String,
    val playlists: List<Playlists>,
    val searchHistories: List<SearchHistory>,
    val song: List<Any>,
    val userName: String
)