package com.example.harish.grocery.repo

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.network.INetworkProductRepo

internal class ProductRepoImpl internal constructor(
        var networkProductRepo: INetworkProductRepo
) : IProductRepo {

    override fun fetchProducts(page: Int): ArrayList<BriefProductInfo> = fetchProducts(page, DEFAULT_PAGE_SIZE)

    override fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo> = networkProductRepo.fetchProducts(page, pageSize)

    override fun fetchProductById(productId: String): ProductInfo? = networkProductRepo.fetchProductById(productId)
}