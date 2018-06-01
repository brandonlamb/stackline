package com.stackline.kafka

data class Product(
  var id: String = "",
  var description: String = "",
  var seller: Seller = Seller(0, ""),
  var category: Category = Category(0, "")
)

data class Category(var id: Long = 0, var description: String = "")
data class Seller(var id: Long = 0, var username: String = "")
