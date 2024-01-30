package com.example.revest_assignment.api

import com.example.revest_assignment.model.Product
import com.example.revest_assignment.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ProductsApi {
    @Headers(
        "Accept: application/json"
    )
    // For All Products
    @GET("products")
    abstract fun getProducts(): Call<Products?>?

     // For specific product
    @GET("products/{id}")
    abstract fun getProductDetails(@Path("id") userId: Int): Call<Product?>?
}