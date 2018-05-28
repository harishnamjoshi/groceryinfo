package com.example.harish.grocery.grocerydetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.example.harish.grocery.R
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.util.PRODUCT_BRIEF_INFO
import kotlinx.android.synthetic.main.activity_grocery_details.*

class GroceryDetailsActivity : AppCompatActivity(), IGroceryDetailsContract.View {

    override var briefProductInfo: BriefProductInfo
        get() = pId
        set(value) {
            pId = value
        }

    private lateinit var pId: BriefProductInfo


    override var presenter: IGroceryDetailsContract.Presenter? = null

    companion object {

        fun newIntent(context: Context, product: BriefProductInfo): Intent {
            val intent = Intent(context, GroceryDetailsActivity::class.java)
            intent.putExtra(PRODUCT_BRIEF_INFO, product)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_details)
        briefProductInfo = savedInstanceState?.getParcelable(PRODUCT_BRIEF_INFO)
                ?: intent.getParcelableExtra(PRODUCT_BRIEF_INFO)
        init(this)
        presenter?.connected()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(PRODUCT_BRIEF_INFO, briefProductInfo)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    override fun setTitle(title: String) {
        detail_title.text = title
    }

    override fun setPrice(price: String) {
        detail_price.text = price
    }

    override fun setDescription(description: String?) {
        detail_description.text = description
    }

    override fun setIcon(icon: String?) {
        Glide.with(this).load(icon).into(detail_icon)
    }

    override fun showNoDataPresent() {
        Snackbar.make(detail_description, getString(R.string.data_not_available), Snackbar.LENGTH_SHORT).show()
    }

    override fun showUnknownError() {
        Snackbar.make(detail_description, getString(R.string.unknown_error), Snackbar.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        Snackbar.make(detail_description, getString(R.string.no_network_connection), Snackbar.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        descLoadingIndicator.visibility = View.GONE
    }

    override fun showLoading() {
        descLoadingIndicator.visibility = View.VISIBLE
    }

    override fun showDescription() {
        detail_description.visibility = View.VISIBLE
    }

    override fun hideDescription() {
        detail_description.visibility = View.GONE
    }
}
