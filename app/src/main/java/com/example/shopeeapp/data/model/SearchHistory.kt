package com.example.shopeeapp.data.model

data class SearchHistory(
    val id: Int,
    val searchQuery: String,
    val searchTime: String,
    val userId: Int
)