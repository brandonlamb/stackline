package com.stackline.product.domain.api

import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import io.reactivex.Single

/**
 * Product Read Infrastructure Service
 */
interface ProductReadRepository {
  /**
   * Find a product by it's product id
   */
  fun findById(id: Single<ProductId>): Single<Product>
}
