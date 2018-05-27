package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.IProductRepo
import com.example.harish.grocery.repo.createProductRepo

class GroceryDetailsModel : IGroceryDetailsContract.Model {

    private val productRepo: IProductRepo = createProductRepo()

    override fun fetchProductInfo(productId: String): ProductInfo? = productRepo.fetchProductById(productId)
}