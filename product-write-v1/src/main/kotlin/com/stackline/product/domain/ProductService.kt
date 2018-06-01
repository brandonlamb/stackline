package com.stackline.product.domain

import com.stackline.product.domain.api.ProductService
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Primary
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class ProductService @Inject constructor(
  private val productWriteRepository: ProductWriteRepository
) : ProductService {
  override fun create(product: Product): Single<Product> =
    Single.just(product).flatMap { productWriteRepository.create(it) }
}
