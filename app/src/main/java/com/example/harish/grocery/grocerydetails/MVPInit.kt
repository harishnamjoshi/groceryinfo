package com.example.harish.grocery.grocerydetails

fun init(view: IGroceryDetailsContract.View) {
    view.presenter = GroceryDetailsPresenter(view, GroceryDetailsModel())
}