package com.example.harish.grocery.repo.network

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import java.net.SocketTimeoutException

interface INetworkProductRepo {

    @Throws(SocketTimeoutException::class)
    fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo>

    @Throws(SocketTimeoutException::class)
    fun fetchProductById(productId: String): ProductInfo?

}