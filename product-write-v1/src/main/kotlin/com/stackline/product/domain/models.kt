package com.stackline.product.domain

import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

typealias ProductId = String
typealias CategoryId = Long
typealias SellerId = Long
typealias Description = String
typealias Username = String

data class Product(
  @Min(8) @Max(16) val id: ProductId,
  @Min(1) @Max(255) val description: Description,
  @Valid val seller: Seller,
  @Valid val category: Category
)

data class Category(
  @Min(0) val id: CategoryId,
  @Min(1) @Max(255) val description: Description
)

data class Seller(
  @Min(0) val id: SellerId,
  @Min(1) @Max(255) val username: Username
)

// Commands

data class CreateProduct(val product: Product)

// Events

data class ProductCreated(val product: Product)
data class CreateProductFailed(val product: Product)
