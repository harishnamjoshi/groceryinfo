package com.example.harish.grocery.grocerylist

fun init(view: IGroceriesContract.View) {


    view.presenter = GroceriesPresenter(view, GroceriesModel())

}