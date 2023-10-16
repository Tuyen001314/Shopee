package com.example.shopeeapp.shared.data.repository.api

import android.util.Log
import com.example.shopeeapp.product_details.business.ProductDetails
import com.example.shopeeapp.product_list.business.Product
import com.example.shopeeapp.shared.business.ProductRepository
import com.example.shopeeapp.shared.business.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

class ProductRepositoryAPI @Inject constructor(private val service: ProductService) :
    ProductRepository {

    override suspend fun getProductList(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getProductList().map {
                    Product(
                        it.title, it.description, it.price, it.imageUrl, it.id
                    )
                }
                if (data.isNotEmpty()) {
                    Result.Success(data)
                } else {
                    Result.Error(IllegalStateException(""))
                }
            } catch (e: Exception) {
                Log.d("NetworkLayer", e.message, e)
                Result.Error(e)
            }
        }
    }

    override suspend fun getProductDetails(productId: String): Result<ProductDetails> {
        return withContext(Dispatchers.IO) {
            try {
                service.getProductDetails(productId).run {
                    Result.Success(
                        ProductDetails(
                            this.title,
                            this.description,
                            this.full_description,
                            "US $ ${this.price}",
                            this.imageUrl,
                            this.pros,
                            this.cons
                        )
                    )
                }
            } catch (exception: Exception) {
                Log.e("NetworkLayer", exception.message, exception)
                Result.Error(exception)
            }
        }
    }

}