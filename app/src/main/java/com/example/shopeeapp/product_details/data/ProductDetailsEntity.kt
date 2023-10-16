package com.example.shopeeapp.product_details.data

data class ProductDetailsEntity (
    val title: String,
    val description: String,
    val full_description: String,
    val price: String,
    val imageUrl: String,
    val pros: List<String>,
    val cons: List<String>
)