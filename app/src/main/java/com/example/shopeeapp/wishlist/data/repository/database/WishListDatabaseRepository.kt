package com.example.shopeeapp.wishlist.data.repository.database

import com.example.shopeeapp.wishlist.business.WishListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishListDatabaseRepository @Inject constructor(
    private val databaseDAO: WishListDAO
) : WishListRepository {

    override suspend fun isFavorite(productId: String): Boolean {
        return withContext(Dispatchers.IO) {
            databaseDAO.isProductFavorite(productId) != null
        }
    }

    override suspend fun addToWishlist(productId: String) {
        return withContext(Dispatchers.IO) {
            databaseDAO.addProductToFavorites(
                FavoriteProductEntity(productId, "")
            )
        }
    }

    override suspend fun removeFromWishlist(productId: String) {
        return withContext(Dispatchers.IO) {
            databaseDAO.removeProductFromFavorites(
                FavoriteProductEntity(productId, "")
            )
        }
    }
}