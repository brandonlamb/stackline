package com.stackline.rest

import com.stackline.product.domain.Category as DomainCategory
import com.stackline.product.domain.Product as DomainProduct
import com.stackline.product.domain.Seller as DomainSeller

data class Product(
  var id: String = "",
  var description: String = "",
  var sellerId: Long = 0,
  var sellerUsername: String = "",
  var categoryId: Long = 0,
  var categoryDescription: String = ""
)

data class Category(var id: Long = 0, var description: String = "")
data class Seller(var id: Long = 0, var username: String = "")

internal fun Product.toDomain() = DomainProduct(
  id = id,
  description = description,
  category = DomainCategory(categoryId, categoryDescription),
  seller = DomainSeller(sellerId, sellerUsername)
)
