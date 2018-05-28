package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.repo.DEFAULT_PAGE
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.net.UnknownHostException

class GroceriesPresenter(view: IGroceriesContract.View, private var model: IGroceriesContract.Model)
    : IGroceriesContract.Presenter {

    private var view: WeakReference<IGroceriesContract.View> = WeakReference(view)
    private var disposable: CompositeDisposable = CompositeDisposable()

    private var isLoading: Boolean = false

    private var page: Int = DEFAULT_PAGE

    private val pageSize: Int = 21


    override fun connected() {
        view.get()?.hideGroceries()
        view.get()?.showLoading()
        loadData()
    }

    override fun reachedEnd() {
        loadData()
    }

    private fun loadData() {

        if (isLoading) return

        isLoading = true
        disposable.add(
                Single.just(true)
                        .map { model.fetchPage(page, pageSize) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    view.get()?.showGroceries()
                                    view.get()?.hideLoading()
                                    page++
                                    isLoading = false
                                    if (it.isEmpty()) {
                                        view.get()?.showNoDataPresent() ?: NullPointerException()
                                    } else {
                                        view.get()?.displayProducts(it) ?: NullPointerException()
                                    }

                                },
                                {
                                    view.get()?.showGroceries()
                                    view.get()?.hideLoading()
                                    isLoading = false
                                    when (it) {
                                        is NullPointerException -> view.get()?.showUnknownError()
                                        is UnknownHostException -> view.get()?.showNetworkError()
                                        else -> view.get()?.showUnknownError()

                                    }
                                }
                        ))
    }

    override fun detach() {
        view.clear()
        disposable.dispose()
    }

}