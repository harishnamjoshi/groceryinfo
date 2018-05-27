package com.example.harish.grocery.repo.network

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.mapper.Mapper
import okhttp3.ResponseBody

class NetworkProductRepoImpl(
        var productService: IProductsService,

        var jsonObjectToProductListMapper: Mapper<ResponseBody?, ArrayList<BriefProductInfo>>,

        var jsonObjectToProductMapper: Mapper<ResponseBody?, ProductInfo?>

) : INetworkProductRepo {

    override fun fetchProducts(page: Int, pageSize: Int): ArrayList<BriefProductInfo> = jsonObjectToProductListMapper.map(productService.fetchProducts(page, pageSize)
            .execute()
            .body())

    override fun fetchProductById(productId: String): ProductInfo? = jsonObjectToProductMapper.map(productService.fetchProductById(productId)
            .execute()
            .body())
}