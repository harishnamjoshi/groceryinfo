package com.example.harish.grocery.repo.network

import com.example.harish.grocery.repo.mapper.JSONObjectToBriefProductInfoMapper
import com.example.harish.grocery.repo.mapper.ResponseBodyToProductInfoListMapper
import com.example.harish.grocery.repo.mapper.ResponseBodyToProductInfoMapper
import com.example.harish.grocery.repo.validator.ResponseBodyValidator
import retrofit2.Retrofit

private var BASE_URL = "https://api.redmart.com/v1.6.0/catalog/"

fun createNetworkProductRepo(): INetworkProductRepo = NetworkProductRepoImpl(
        createNetworkService(),
        ResponseBodyToProductInfoListMapper(JSONObjectToBriefProductInfoMapper(), ResponseBodyValidator()),
        ResponseBodyToProductInfoMapper(JSONObjectToBriefProductInfoMapper(), ResponseBodyValidator())
)

fun createNetworkService(): IProductsService = buildServer().create(IProductsService::class.java)

fun buildServer(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()