package com.example.harish.grocery.repo.mapper

const val BASE_IMAGE_URL = "http://media.redmart.com/newmedia/200p"

const val JSON_PRODUCTS = "products"

const val JSON_PRODUCT = "product"

const val JSON_PRODUCT_ID = "id"

const val JSON_PRODUCT_TITLE = "title"

const val JSON_IMAGE_INFO_OBJECT = "img"

const val JSON_IMAGE_NAME = "name"

const val JSON_PRICING_INFO_OBJECT = "pricing"

const val JSON_PRICE = "price"

const val JSON_DESCRIPTION = "desc"

const val CURRENCY = "$"

const val JSON_STATUS = "status"

const val JSON_CODE = "code"

const val DATA_NOT_AVAILABLE = 404

fun createImageUrl(name: String): String = BASE_IMAGE_URL + name