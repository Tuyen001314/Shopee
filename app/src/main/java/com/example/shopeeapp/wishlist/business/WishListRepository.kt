package com.example.shopeeapp.wishlist.business

interface WishListRepository {
    suspend fun isFavorite(productId: String): Boolean
    suspend fun addToWishlist(productId: String)
    suspend fun removeFromWishlist(productId: String)
}