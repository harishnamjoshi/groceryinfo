package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.repo.createProductRepo

fun init(view: IGroceryDetailsContract.View) {
    view.presenter = GroceryDetailsPresenter(view, GroceryDetailsModel(createProductRepo()))
}