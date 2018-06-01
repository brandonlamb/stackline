package com.stackline.aws

typealias ProductId = String
typealias CategoryId = Long
typealias SellerId = Long
typealias Description = String
typealias Username = String

data class Product(
  var id: ProductId = "",
  var description: Description = "",
  var seller: Seller = Seller(0, ""),
  var category: Category = Category(0, "")
)

data class Category(
  var id: CategoryId = 0,
  var description: Description = ""
)

data class Seller(
  var id: SellerId = 0,
  var username: Username = ""
)
