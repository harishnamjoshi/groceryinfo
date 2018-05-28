package com.example.harish.grocery.grocerydetails

import com.example.harish.grocery.DUMMY
import com.example.harish.grocery.INVALID
import com.example.harish.grocery.VALID_ID
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.IProductRepo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GroceryDetailsModelTest {

    private lateinit var productRepo: IProductRepo

    private lateinit var model: IGroceryDetailsContract.Model

    private val briefProductInfo = BriefProductInfo(DUMMY, DUMMY, DUMMY, DUMMY, DUMMY)

    private val productInfo = ProductInfo(briefProductInfo, DUMMY)

    @Before
    fun setUp() {

        productRepo = Mockito.mock(IProductRepo::class.java)
        model = GroceryDetailsModel(productRepo)

    }

    @Test
    fun fetchProductInfoInValid() {

        Mockito.`when`(productRepo.fetchProductById(INVALID)).thenReturn(null)

        assertNull(model.fetchProductInfo(INVALID))

    }

    @Test
    fun fetchProductInfoValid() {

        Mockito.`when`(productRepo.fetchProductById(VALID_ID)).thenReturn(productInfo)

        assertNotNull(model.fetchProductInfo(VALID_ID))

    }
}