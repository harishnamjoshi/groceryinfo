package com.example.harish.grocery.repo

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo

interface IProductRepo {

    fun fetchProducts(page: Int): ArrayList<BriefProductInfo>

    fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo>

    fun fetchProductById(productId: String): ProductInfo?

}