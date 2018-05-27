package com.example.harish.grocery.grocerylist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.harish.grocery.R
import com.example.harish.grocery.model.BriefProductInfo

class ProductViewHolder(
        itemView: View, productClickListener: IProductClickListener
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { productClickListener.onProductClicked(this.item, this.icon) }
    }

    val title = itemView.findViewById<TextView>(R.id.title)

    val price = itemView.findViewById<TextView>(R.id.price)

    val icon = itemView.findViewById<ImageView>(R.id.icon)


    lateinit var item: BriefProductInfo

    fun init(item: BriefProductInfo) {
        this.item = item

        title.text = item.title

        price.text = item.currency + item.price

        Glide.with(icon.context).load(item.imgUrl).into(icon)

    }

}