package com.example.harish.grocery.util

import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.harish.grocery.R
import com.example.harish.grocery.grocerydetails.GroceryDetailsActivity
import com.example.harish.grocery.model.BriefProductInfo


fun launchProductDetails(context: AppCompatActivity, briefProductInfo: BriefProductInfo, view: View?) {
    var options: ActivityOptionsCompat? = null
    if (view != null) {
        options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context, view, context.getString(R.string.transition_name_product_icon)
        )
    }
    ActivityCompat.startActivity(
            context, GroceryDetailsActivity.newIntent(context, briefProductInfo), options?.toBundle()
    )

}