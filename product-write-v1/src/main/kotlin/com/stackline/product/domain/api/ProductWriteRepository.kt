package com.stackline.product.domain.api

import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import io.reactivex.Single

/**
 * Product Write Infrastructure Service
 */
interface ProductWriteRepository {
  /**
   * Create a product (duh, right?)
   */
  fun create(product: Product): Single<Product>

  /**
   * Update a product (duh, right?)
   */
  fun update(product: Product): Single<Void>

  /**
   * Update a product (duh, right?)
   */
  fun removeById(id: ProductId): Single<Void>
}
