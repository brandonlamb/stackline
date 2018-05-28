package com.stackline

object Config {
  const val URL = "https://s3-us-west-2.amazonaws.com/stackline-public/sample_product_data.tsv.gz"
  const val DOWNLOAD_FILE = "data.tdf.gz"
  const val DATA_FILE = "data.tdf"
  const val CTX_POOL_SIZE = 32
  const val PRODUCT_READ_URL = "http://localhost:8080/v1/products"
  const val PRODUCT_WRITE_URL = "http://localhost:8080/v1/products"
}
