package com.stackline.product.domain.api

import com.stackline.product.domain.Product
import io.reactivex.Single

/**
 * Product Write Infrastructure Service
 */
interface ProductWriteRepository {
  /**
   * Create a product, returning a reactive stream with the product
   */
  fun create(product: Product): Single<Product>
}
