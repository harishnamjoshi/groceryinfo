package com.example.harish.grocery.grocerylist

import com.example.harish.grocery.DUMMY
import com.example.harish.grocery.INVALID_PAGE
import com.example.harish.grocery.PAGE_SIZE
import com.example.harish.grocery.VALID_PAGE
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.repo.IProductRepo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GroceriesModelTest {

    private lateinit var productRepo: IProductRepo

    private lateinit var model: IGroceriesContract.Model

    private val briefProductInfo = BriefProductInfo(DUMMY, DUMMY, DUMMY, DUMMY, DUMMY)

    private val nonEmptyList = ArrayList<BriefProductInfo>()

    @Before
    fun setUp() {
        nonEmptyList.add(briefProductInfo)
        productRepo = Mockito.mock(IProductRepo::class.java)
        model = GroceriesModel(productRepo)

    }

    @Test
    fun fetchPage() {

        Mockito.`when`(productRepo.fetchProducts(VALID_PAGE, PAGE_SIZE)).thenReturn(nonEmptyList)
        assertNotEquals(model.fetchPage(VALID_PAGE, PAGE_SIZE).size, 0)

        Mockito.`when`(productRepo.fetchProducts(INVALID_PAGE, PAGE_SIZE)).thenReturn(ArrayList())
        assertEquals(model.fetchPage(INVALID_PAGE, PAGE_SIZE).size, 0)

    }
}