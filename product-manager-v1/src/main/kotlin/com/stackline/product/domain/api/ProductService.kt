package com.stackline.product.domain.api

import com.stackline.product.domain.Product

/**
 * Product Domain Service
 */
interface ProductService {
  /**
   * Create a product, perform business logic, etc
   */
  fun create(product: Product)
}
