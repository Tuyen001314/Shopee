package com.example.shopeeapp.di

import android.content.Context
import androidx.room.Room
import com.example.shopeeapp.cart.business.CartRepository
import com.example.shopeeapp.cart.data.CartRepositorySharedPreferences
import com.example.shopeeapp.shared.business.ProductRepository
import com.example.shopeeapp.shared.data.repository.api.ApiClient
import com.example.shopeeapp.shared.data.repository.api.ProductRepositoryAPI
import com.example.shopeeapp.shared.data.repository.api.ProductService
import com.example.shopeeapp.wishlist.data.repository.database.AppDatabase
import com.example.shopeeapp.wishlist.data.repository.database.WishListDAO
import com.example.shopeeapp.wishlist.data.repository.database.WishListDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesProductService(): ProductService = ApiClient.getService()

    @Provides
    @Singleton
    fun providesCartRepository(@ApplicationContext context: Context): CartRepository {
        return CartRepositorySharedPreferences(context)
    }

    @Provides
    fun providesProductRepositoryAPI(
        service: ProductService
    ): ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductRepository(
        productRepositoryAPI: ProductRepositoryAPI
    ): ProductRepository = productRepositoryAPI

    @Provides
    fun providesWishlistRepository(
        databaseRepository: WishListDatabaseRepository
    ): WishListDatabaseRepository = databaseRepository

    @Provides
    fun providesWishlistDatabaseRepository(databaseDAO: WishListDAO): WishListDatabaseRepository {
        return WishListDatabaseRepository(databaseDAO)
    }

    @Provides
    fun providesWishlistDAO(
        @ApplicationContext context: Context
    ): WishListDAO {
        val db = Room.databaseBuilder(
            context, AppDatabase::class.java,
            "shopee app"
        ).build()
        return db.wishListDao()
    }

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}