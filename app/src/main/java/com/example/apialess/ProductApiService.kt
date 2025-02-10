package com.example.apialess

import com.example.apialess.model.Products
import com.example.apialess.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Products>>
}