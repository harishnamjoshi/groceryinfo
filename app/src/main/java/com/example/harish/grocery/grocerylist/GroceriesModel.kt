package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.repo.DEFAULT_PAGE
import com.example.harish.grocery.repo.IProductRepo
import com.example.harish.grocery.repo.createProductRepo

class GroceriesModel : IGroceriesContract.Model {

    private var page: Int = DEFAULT_PAGE

    private val pageSize: Int = 21

    private val productRepoImpl: IProductRepo = createProductRepo()

    override fun next(): ArrayList<BriefProductInfo> {
        val briefInfos: ArrayList<BriefProductInfo> = productRepoImpl.fetchProducts(page, pageSize)
        page++
        return briefInfos
    }
}