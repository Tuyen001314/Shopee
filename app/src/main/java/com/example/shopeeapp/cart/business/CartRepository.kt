package com.example.shopeeapp.cart.business

import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun observeChanges(): Flow<List<String>>
    suspend fun addToCart(productId: String)
    suspend fun removeFromCart(productId: String)
}