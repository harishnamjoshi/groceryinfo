package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.repo.IProductRepo

class GroceriesModel(
        private val productRepo: IProductRepo
) : IGroceriesContract.Model {

    override fun fetchPage(page: Int, pageSize: Int): ArrayList<BriefProductInfo> = productRepo.fetchProducts(page, pageSize)
}