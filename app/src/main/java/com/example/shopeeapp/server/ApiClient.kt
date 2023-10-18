package com.example.shopeeapp.server

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {

    @GET
    suspend fun getStringSuspend(@Url url: String): String
}