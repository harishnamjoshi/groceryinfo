package com.example.harish.grocery.repo.mapper

interface Mapper<InParam, OutValue> {

    fun map(param: InParam): OutValue

}