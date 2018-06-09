package com.stackline

/**
 * Extension function to convert a line from the flat-file source into a product
 */
fun String.toProduct(): Product {
  // Parse the tab-delimited value
  val p = split("\t")

  return Product(
    id = p[0],
    description = p[1],
    sellerId = p[2].toLong(),
    sellerName = p[3],
    categoryId = p[4].toLong(),
    categoryName = p[5]
  )
}

data class Product(
  val id: String,
  val description: String,
  val sellerId: Long,
  val sellerName: String,
  val categoryId: Long,
  val categoryName: String
)

data class DefaultConfig(
  val apiProductRead: String,
  val apiProductWrite: String,
  val ctxPoolSize: Int,
  val dataFilename: String,
  val dataUrl: String,
  val downloadFile: Boolean,
  val esHost: String,
  val esPort: Int
)
