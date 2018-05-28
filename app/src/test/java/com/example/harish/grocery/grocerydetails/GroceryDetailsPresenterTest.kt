package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.DUMMY
import com.example.harish.grocery.ONCE
import com.example.harish.grocery.VALID_ID
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException

class GroceryDetailsPresenterTest {

    private lateinit var view: IGroceryDetailsContract.View
    private lateinit var model: IGroceryDetailsContract.Model
    private lateinit var presenter: IGroceryDetailsContract.Presenter


    private val briefProductInfo = BriefProductInfo(VALID_ID, DUMMY, DUMMY, DUMMY, DUMMY)
    private val productInfo = ProductInfo(briefProductInfo, DUMMY)

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        view = Mockito.mock(IGroceryDetailsContract.View::class.java)
        model = Mockito.mock(IGroceryDetailsContract.Model::class.java)
        presenter = GroceryDetailsPresenter(view, model)
        Mockito.`when`(view.briefProductInfo).thenReturn(briefProductInfo)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun connectedLoadingWithValidData() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenReturn(productInfo)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideDescription()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showDescription()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()

    }

    @Test
    fun connectedLoadingWithNoData() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenReturn(null)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideDescription()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showDescription()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showNoDataPresent()
    }

    @Test
    fun connectedLoadingWithNetworkError() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenThrow(UnknownHostException::class.java)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideDescription()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showDescription()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showNetworkError()
    }

    @Test
    fun connectedWithValidId() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenReturn(productInfo)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).briefProductInfo
        Mockito.verify(view, Mockito.times(ONCE)).setPrice(briefProductInfo.currency + briefProductInfo.price)
        Mockito.verify(view, Mockito.times(ONCE)).setIcon(briefProductInfo.imgUrl)
        Mockito.verify(view, Mockito.times(ONCE)).setTitle(briefProductInfo.title)
        Mockito.verify(view, Mockito.times(ONCE)).setDescription(productInfo.details)
    }

    @Test
    fun connectedWithInValidId() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenReturn(null)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).briefProductInfo
        Mockito.verify(view, Mockito.times(ONCE)).setPrice(briefProductInfo.currency + briefProductInfo.price)
        Mockito.verify(view, Mockito.times(ONCE)).setIcon(briefProductInfo.imgUrl)
        Mockito.verify(view, Mockito.times(ONCE)).setTitle(briefProductInfo.title)
        Mockito.verify(view, Mockito.times(ONCE)).showNoDataPresent()
    }

    @Test
    fun connectedWithNetworkError() {
        Mockito.`when`(model.fetchProductInfo(briefProductInfo.productId)).thenThrow(UnknownHostException::class.java)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).briefProductInfo
        Mockito.verify(view, Mockito.times(ONCE)).setPrice(briefProductInfo.currency + briefProductInfo.price)
        Mockito.verify(view, Mockito.times(ONCE)).setIcon(briefProductInfo.imgUrl)
        Mockito.verify(view, Mockito.times(ONCE)).setTitle(briefProductInfo.title)
        Mockito.verify(view, Mockito.times(ONCE)).showNetworkError()
    }
}