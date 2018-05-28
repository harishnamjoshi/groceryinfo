package com.example.harish.grocery.repo.network

import com.example.harish.grocery.INVALID
import com.example.harish.grocery.INVALID_PAGE
import com.example.harish.grocery.PAGE_SIZE
import org.junit.Assert.*
import org.junit.Test
import java.net.SocketTimeoutException

class NetworkRepoTest {

    private val iNetworkProductRepo = createNetworkProductRepo()

    @Test
    fun fetchProducts() {

        try {
            assertEquals(iNetworkProductRepo.fetchProducts(INVALID_PAGE, PAGE_SIZE).size, 0)
        } catch (exception: SocketTimeoutException) {
            assertNotNull(exception)
        }


    }

    @Test
    fun fetchProductById() {

        try {
            assertNull(iNetworkProductRepo.fetchProductById(INVALID))
        } catch (exception: SocketTimeoutException) {
            assertNotNull(exception)
        }


    }
}