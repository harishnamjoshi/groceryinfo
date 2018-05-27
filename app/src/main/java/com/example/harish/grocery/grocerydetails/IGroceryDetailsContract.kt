package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo

interface IGroceryDetailsContract {

    interface View {
        fun noDataPresent()

        fun showUnknownError()

        fun showNetworkError()

        fun setTitle(title: String)

        fun setPrice(price: String)

        fun setDescription(description: String?)

        fun setIcon(icon: String?)

        var presenter: Presenter?

        var briefProductInfo: BriefProductInfo
    }

    interface Presenter {
        fun connected()

        fun detach()
    }

    interface Model {
        fun fetchProductInfo(productId: String): ProductInfo?
    }

}