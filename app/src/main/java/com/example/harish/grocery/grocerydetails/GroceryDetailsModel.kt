package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.IProductRepo

class GroceryDetailsModel(
        private val productRepo: IProductRepo
) : IGroceryDetailsContract.Model {

    override fun fetchProductInfo(productId: String): ProductInfo? = productRepo.fetchProductById(productId)
}