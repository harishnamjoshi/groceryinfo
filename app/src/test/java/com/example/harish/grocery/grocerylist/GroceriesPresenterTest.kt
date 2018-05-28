package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.DUMMY
import com.example.harish.grocery.ONCE
import com.example.harish.grocery.model.BriefProductInfo
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException

class GroceriesPresenterTest {

    private lateinit var view: IGroceriesContract.View
    private lateinit var model: IGroceriesContract.Model
    private lateinit var presenter: IGroceriesContract.Presenter

    private val briefProductInfo = BriefProductInfo(DUMMY, DUMMY, DUMMY, DUMMY, DUMMY)

    private val nonEmptyList = ArrayList<BriefProductInfo>()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        nonEmptyList.add(briefProductInfo)
        view = Mockito.mock(IGroceriesContract.View::class.java)
        model = Mockito.mock(IGroceriesContract.Model::class.java)
        presenter = GroceriesPresenter(view, model)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun connectedLoadingWithValidData() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(nonEmptyList)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()

    }

    @Test
    fun connectedLoadingWithEmptyList() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(ArrayList())
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showNoDataPresent()
    }

    @Test
    fun connectedLoadingWithNetworkError() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenThrow(UnknownHostException::class.java)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).hideGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).showLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showGroceries()
        Mockito.verify(view, Mockito.times(ONCE)).hideLoading()
        Mockito.verify(view, Mockito.times(ONCE)).showNetworkError()
    }


    @Test
    fun connectedWithNonEmptyList() {
        //Valid Data available
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(nonEmptyList)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).displayProducts(nonEmptyList)
    }

    @Test
    fun connectedWithEmptyList() {
        //Empty Data available
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(ArrayList())
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).showNoDataPresent()
    }

    @Test
    fun connectedWithNetworkError() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenThrow(UnknownHostException::class.java)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).showNetworkError()
    }

    @Test
    fun reachedEndWithNonEmptyList() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(nonEmptyList)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).displayProducts(nonEmptyList)
    }

    @Test
    fun reachedEndWithEmptyList() {
        //Empty Data available
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(ArrayList())
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).showNoDataPresent()
    }

    @Test
    fun reachedEndWithNetworkError() {
        Mockito.`when`(model.fetchPage(Mockito.anyInt(), Mockito.anyInt())).thenThrow(UnknownHostException::class.java)
        presenter.connected()
        Mockito.verify(view, Mockito.times(ONCE)).showNetworkError()
    }
}