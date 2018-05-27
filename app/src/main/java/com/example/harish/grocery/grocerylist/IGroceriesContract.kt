package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.model.BriefProductInfo

interface IGroceriesContract {


    interface View {

        var presenter: Presenter?

        fun displayProducts(products: ArrayList<BriefProductInfo>)

        fun noDataPresent()

        fun showUnknownError()

        fun showNetworkError()
    }

    interface Presenter {
        fun connected()

        fun detach()

        fun reachedEnd()


    }

    interface Model {

        fun next(): ArrayList<BriefProductInfo>

    }

}