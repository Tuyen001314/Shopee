package com.example.shopeeapp.shared.business

import com.example.shopeeapp.product_details.business.ProductDetails
import com.example.shopeeapp.product_list.business.Product

interface ProductRepository {

    suspend fun getProductList(): Result<List<Product>>

    suspend fun getProductDetails(productId: String): Result<ProductDetails>
}