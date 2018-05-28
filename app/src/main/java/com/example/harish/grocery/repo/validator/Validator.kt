package com.example.harish.grocery.repo.validator

interface Validator<Data> {

    fun validate(param: Data): Boolean

}