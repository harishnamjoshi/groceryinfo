package com.example.harish.grocery.repo.mapper

val BASE_IMAGE_URL = "http://media.redmart.com/newmedia/200p"

val JSON_PRODUCTS = "products"

val JSON_PRODUCT = "product"

val JSON_PRODUCT_ID = "id"

val JSON_PRODUCT_TITLE = "title"

val JSON_IMAGE_INFO_OBJECT = "img"

val JSON_IMAGE_NAME = "name"

val JSON_PRICING_INFO_OBJECT = "pricing"

val JSON_PRICE = "price"

val JSON_DESCRIPTION = "desc"

val CURRENCY = "$"

val JSON_STATUS = "status"

val JSON_CODE = "code"

fun createImageUrl(name: String): String = BASE_IMAGE_URL + name