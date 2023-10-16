package com.example.shopeeapp.product_details.presentation

import com.example.shopeeapp.product_details.business.ProductDetails

sealed class ProductDetailsViewState {
    object Loading: ProductDetailsViewState()
    data class Content(val product: ProductDetails): ProductDetailsViewState()
    object Error: ProductDetailsViewState()
}