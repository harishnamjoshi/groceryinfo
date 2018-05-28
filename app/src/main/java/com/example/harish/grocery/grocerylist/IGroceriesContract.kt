package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.model.BriefProductInfo
import java.net.UnknownHostException

interface IGroceriesContract {


    interface View {

        var presenter: Presenter?

        fun displayProducts(products: ArrayList<BriefProductInfo>)

        fun showNoDataPresent()

        fun showUnknownError()

        fun showNetworkError()

        fun showLoading()

        fun hideLoading()

        fun showGroceries()

        fun hideGroceries()
    }

    interface Presenter {
        fun connected()

        fun detach()

        fun reachedEnd()

    }

    interface Model {

        @Throws(UnknownHostException::class)
        fun fetchPage(page: Int, pageSize: Int): ArrayList<BriefProductInfo>

    }

}