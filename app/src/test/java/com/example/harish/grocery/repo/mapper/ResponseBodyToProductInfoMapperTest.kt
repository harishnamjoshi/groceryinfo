package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.repo.validator.ResponseBodyValidator
import org.junit.Assert.assertNull
import org.junit.Test

class ResponseBodyToProductInfoMapperTest {

    @Test
    fun map() {
        assertNull(ResponseBodyToProductInfoMapper(
                JSONObjectToBriefProductInfoMapper(), ResponseBodyValidator()
        ).map(null))
    }
}