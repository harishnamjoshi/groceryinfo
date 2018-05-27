package com.example.harish.grocery.repo.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IProductsService {

    @GET("search")
    fun fetchProducts(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Call<ResponseBody>

    @GET("products/{productId}")
    fun fetchProductById(@Path("productId") productId: String): Call<ResponseBody>

}