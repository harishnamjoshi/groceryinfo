package com.example.harish.grocery.repo.network

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo

interface INetworkProductRepo {

    fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo>

    fun fetchProductById(productId: String): ProductInfo?

}