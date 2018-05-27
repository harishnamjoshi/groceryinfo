package com.example.harish.grocery.grocerylist

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


    override fun connected() {
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
                        .map { model.next() }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    isLoading = false
                                    view.get()?.displayProducts(it) ?: NullPointerException()
                                },
                                {
                                    isLoading = false
                                    when (it) {
                                        is NullPointerException -> view.get()?.noDataPresent()
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