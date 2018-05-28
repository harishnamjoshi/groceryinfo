package com.example.harish.grocery.repo.validator

interface Validator<Data> {

    fun validate(data: Data): Boolean

}