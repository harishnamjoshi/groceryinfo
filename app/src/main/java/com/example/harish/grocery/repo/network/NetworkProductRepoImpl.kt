package com.example.harish.grocery.repo.network

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.mapper.Mapper

class NetworkProductRepoImpl(
        var productService: IProductsService,

        var jsonObjectToProductListMapper: Mapper<String?, ArrayList<BriefProductInfo>>,

        var jsonObjectToProductMapper: Mapper<String?, ProductInfo?>

) : INetworkProductRepo {

    override fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo> = jsonObjectToProductListMapper.map(productService.fetchProducts(page, pageSize)
            .execute()
            .body()
            ?.string()
    )

    override fun fetchProductById(productId: String): ProductInfo? = jsonObjectToProductMapper.map(productService.fetchProductById(productId)
            .execute()
            .body()
            ?.string()
    )
}