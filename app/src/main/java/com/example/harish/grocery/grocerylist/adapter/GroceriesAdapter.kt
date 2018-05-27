package com.example.harish.grocery.grocerylist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.harish.grocery.R
import com.example.harish.grocery.model.BriefProductInfo


class GroceriesAdapter(
        val groceries: ArrayList<BriefProductInfo>,
        private val productClickListener: IProductClickListener
) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        // set the view's size, margins, paddings and layout parameters
        return ProductViewHolder(
                inflater.inflate(R.layout.item_grocery, parent, false),
                productClickListener
        )
    }

    override fun getItemCount(): Int {
        return groceries.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.init(groceries[position])
    }

    fun add(groceries: ArrayList<BriefProductInfo>) {
        val size: Int = groceries.size
        this.groceries.addAll(groceries)
        notifyItemInserted(size)
    }
}