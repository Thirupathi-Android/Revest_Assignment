package com.example.revest_assignment.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.revest_assignment.api.ProductsApi
import com.example.revest_assignment.model.Product
import com.example.revest_assignment.model.Products

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ProductsViewModel: ViewModel() {
    val API_END_POINT="https://dummyjson.com/"

    private val _posts = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _posts

    private val _product = mutableStateOf(Product("","","",0.0,0, emptyList(),0,0.0,0,"",""))
    val product get() = _product.value



    fun getProducts(
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ProductsApi::class.java)
        val call: Call<Products?>? = api.getProducts();

        call!!.enqueue(object: Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                if(response.isSuccessful) {
                    _posts.value = response.body()!!.products
                }
            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }
    fun getProductDetails(productId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ProductsApi::class.java)
        val call: Call<Product?>? = api.getProductDetails(productId);

        call!!.enqueue(object: Callback<Product?> {
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
                if(response.isSuccessful) {
                    _product.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }
}