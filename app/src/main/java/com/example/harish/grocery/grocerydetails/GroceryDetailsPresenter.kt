package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.model.BriefProductInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.net.UnknownHostException

class GroceryDetailsPresenter(view: IGroceryDetailsContract.View, private val model: IGroceryDetailsContract.Model) : IGroceryDetailsContract.Presenter {

    private var view: WeakReference<IGroceryDetailsContract.View> = WeakReference(view)

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun connected() {
        val briefProductInfo: BriefProductInfo? = view.get()?.briefProductInfo
        if (briefProductInfo != null) {
            view.get()?.setTitle(briefProductInfo.title)
            view.get()?.setPrice(briefProductInfo.currency + briefProductInfo.price)
            view.get()?.setIcon(briefProductInfo.imgUrl)
            disposable.add(Single.just(true)
                    .map { model.fetchProductInfo(briefProductInfo.productId) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                if (it != null) {
                                    view.get()?.setDescription(it.details)
                                }
                            },
                            {
                                when (it) {
                                    is NullPointerException -> view.get()?.noDataPresent()
                                    is UnknownHostException -> view.get()?.showNetworkError()
                                    else -> view.get()?.showUnknownError()

                                }
                            }
                    ))
        }

    }

    override fun detach() {
        view.clear()
        disposable.dispose()
    }
}