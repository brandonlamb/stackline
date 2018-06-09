package com.stackline.infra.es

import com.stackline.product.domain.Product as DomainProduct

data class Product(
  val id: String,
  val description: String,
  val sellerId: Long,
  val sellerName: String,
  val categoryId: Long,
  val categoryName: String
)

internal fun DomainProduct.toEs() = Product(
  id = id,
  description = description,
  sellerId = seller.id,
  sellerName = seller.username,
  categoryId = category.id,
  categoryName = category.description
)
