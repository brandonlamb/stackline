package com.stackline.product.domain.api

import com.stackline.product.domain.Product
import io.reactivex.Single

interface ProductWriteRepository {
  fun create(product: Product): Single<Product>
}
