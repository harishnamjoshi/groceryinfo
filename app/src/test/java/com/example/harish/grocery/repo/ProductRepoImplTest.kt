package com.example.harish.grocery.repo

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import com.example.harish.grocery.*
import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.network.INetworkProductRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class ProductRepoImplTest {


    private lateinit var iProductRepo: IProductRepo
    private lateinit var iNetworkProductRepo: INetworkProductRepo
    private val nonEmptyList = ArrayList<BriefProductInfo>()
    private val briefProductInfo = BriefProductInfo(DUMMY, DUMMY, DUMMY, DUMMY, DUMMY)
    private val productInfo = ProductInfo(briefProductInfo, DUMMY)

    @Before
    fun setUp() {
        iNetworkProductRepo = Mockito.mock(INetworkProductRepo::class.java)
        nonEmptyList.add(briefProductInfo)
        iProductRepo = ProductRepoImpl(iNetworkProductRepo)
    }

    @Test
    fun fetchValidProducts() {
        Mockito.`when`(iNetworkProductRepo.fetchProducts(VALID_PAGE, PAGE_SIZE)).thenReturn(nonEmptyList)
        assertNotEquals(iProductRepo.fetchProducts(VALID_PAGE, PAGE_SIZE).size, EMPTY_LIST_SIZE)
    }

    @Test
    fun fetchInValidProducts() {
        Mockito.`when`(iNetworkProductRepo.fetchProducts(INVALID_PAGE, PAGE_SIZE)).thenReturn(ArrayList())
        assertEquals(iProductRepo.fetchProducts(INVALID_PAGE, PAGE_SIZE).size, EMPTY_LIST_SIZE)
    }

    @Test
    fun fetchValidProductById() {
        Mockito.`when`(iNetworkProductRepo.fetchProductById(VALID_ID)).thenReturn(productInfo)
        assertNotNull(iProductRepo.fetchProductById(VALID_ID))
    }

    @Test
    fun fetchInValidProductById() {
        Mockito.`when`(iNetworkProductRepo.fetchProductById(INVALID)).thenReturn(null)
        assertNull(iProductRepo.fetchProductById(INVALID))
    }
}