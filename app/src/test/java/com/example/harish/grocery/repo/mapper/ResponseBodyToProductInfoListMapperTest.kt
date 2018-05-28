package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.repo.validator.ResponseBodyValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseBodyToProductInfoListMapperTest {

    @Test
    fun map() {
        val values = ResponseBodyToProductInfoListMapper(
                JSONObjectToBriefProductInfoMapper(), ResponseBodyValidator()
        ).map(null)

        assertEquals(values.size, 0)
    }
}