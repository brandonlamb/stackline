package com.stackline.product.domain.api

import com.stackline.product.domain.Product

interface ProductService {
  fun create(product: Product)
}
