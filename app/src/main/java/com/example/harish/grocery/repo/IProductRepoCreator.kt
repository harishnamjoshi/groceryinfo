package com.example.harish.grocery.repo

import com.example.harish.grocery.repo.network.createNetworkProductRepo

val DEFAULT_PAGE_SIZE = 20

val DEFAULT_PAGE = 0

fun createProductRepo(): IProductRepo = ProductRepoImpl(createNetworkProductRepo())