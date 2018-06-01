package com.stackline.product.domain.api

import com.stackline.product.domain.Product
import io.reactivex.Single

/**
 * Product Domain Service
 */
interface ProductService {
  /**
   * Create a product
   */
  fun create(product: Product): Single<Product>
}
