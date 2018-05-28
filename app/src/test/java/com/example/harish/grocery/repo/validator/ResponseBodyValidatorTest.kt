package com.example.harish.grocery.repo.validator

import junit.framework.Assert.assertTrue
import org.junit.Test

class ResponseBodyValidatorTest {

    @Test
    fun validate() {
        assertTrue(!ResponseBodyValidator().validate(null))
    }
}