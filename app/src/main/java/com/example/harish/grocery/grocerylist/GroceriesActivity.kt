package com.example.harish.grocery.grocerylist

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.harish.grocery.R
import com.example.harish.grocery.grocerylist.adapter.GroceriesAdapter
import com.example.harish.grocery.grocerylist.adapter.IProductClickListener
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.util.launchProductDetails
import kotlinx.android.synthetic.main.activity_groceries.*


class GroceriesActivity : AppCompatActivity(), IGroceriesContract.View, IProductClickListener {
    override var presenter: IGroceriesContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groceries)
        init(this)
        presenter?.connected()
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    override fun displayProducts(products: ArrayList<BriefProductInfo>) {
        val groceriesAdapter: GroceriesAdapter? = groceries.adapter as? GroceriesAdapter
        if (groceriesAdapter == null) {
            groceries.layoutManager = GridLayoutManager(this, 3)
            groceries.adapter = GroceriesAdapter(products, this)
            groceries.addOnScrollListener(ScrollListener(presenter))
        } else {
            groceriesAdapter.add(products)
        }
    }

    private class ScrollListener(val presenter: IGroceriesContract.Presenter?) : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            //Check if recyclerView is not null & scroll is not negative
            if (recyclerView != null && dy >= 0 && presenter != null) {
                val visibleItemCount = recyclerView.childCount
                val layoutManager: GridLayoutManager? = recyclerView.layoutManager as? GridLayoutManager
                if (layoutManager != null) {
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        presenter.reachedEnd()
                    }
                }

            }
        }
    }

    override fun showNoDataPresent() {
        Snackbar.make(groceries, getString(R.string.data_not_available), Snackbar.LENGTH_SHORT).show()
    }

    override fun showUnknownError() {
        Snackbar.make(groceries, getString(R.string.unknown_error), Snackbar.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        Snackbar.make(groceries, getString(R.string.no_network_connection), Snackbar.LENGTH_SHORT).show()
    }

    override fun onProductClicked(briefProductInfo: BriefProductInfo, imageView: ImageView?) {
        launchProductDetails(this, briefProductInfo, imageView)
    }

    override fun hideLoading() {
        groceriesLoadingIndicator.visibility = View.GONE
    }

    override fun showLoading() {
        groceriesLoadingIndicator.visibility = View.VISIBLE
    }

    override fun showGroceries() {
        groceries.visibility = View.VISIBLE
    }

    override fun hideGroceries() {
        groceries.visibility = View.INVISIBLE
    }
}
