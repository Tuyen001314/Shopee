package com.example.shopeeapp.data.model

data class Playlists(
    val createdAt: String,
    val creator: Int,
    val favorite: Boolean,
    val id: Int,
    val name: String,
    val songs: List<SongRemote>
)