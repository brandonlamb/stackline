package com.stackline.product.domain

import com.stackline.product.domain.api.ProductService
import com.stackline.product.domain.api.ProductWriteRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService @Inject constructor(private val repos: List<ProductWriteRepository>) : ProductService {
  override fun create(product: Product) {
    // This is where business logic would be run, e.g. validations, transformations
    Single
      .amb(repos.map { it.create(product) })
      .subscribeOn(Schedulers.io())
      .subscribe()
  }
}
