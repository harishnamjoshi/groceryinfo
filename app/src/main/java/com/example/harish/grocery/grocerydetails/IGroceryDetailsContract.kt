package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import java.net.UnknownHostException

interface IGroceryDetailsContract {

    interface View {


        fun setTitle(title: String)

        fun setPrice(price: String)

        fun setDescription(description: String?)

        fun setIcon(icon: String?)

        var presenter: Presenter?

        var briefProductInfo: BriefProductInfo

        fun showLoading()

        fun hideLoading()

        fun showUnknownError()

        fun showNetworkError()

        fun showDescription()

        fun hideDescription()

        fun showNoDataPresent()
    }

    interface Presenter {
        fun connected()

        fun detach()
    }

    interface Model {
        @Throws(UnknownHostException::class)
        fun fetchProductInfo(productId: String): ProductInfo?
    }

}