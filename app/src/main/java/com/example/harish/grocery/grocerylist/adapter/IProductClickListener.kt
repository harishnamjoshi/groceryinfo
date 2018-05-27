package com.example.harish.grocery.grocerylist.adapter

import android.widget.ImageView
import com.example.harish.grocery.model.BriefProductInfo

interface IProductClickListener {

    fun onProductClicked(briefProductInfo: BriefProductInfo, imageView: ImageView?)

}