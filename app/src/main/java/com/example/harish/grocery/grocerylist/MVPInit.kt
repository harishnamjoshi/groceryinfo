package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.repo.createProductRepo

fun init(view: IGroceriesContract.View) {


    view.presenter = GroceriesPresenter(view, GroceriesModel(createProductRepo()))

}