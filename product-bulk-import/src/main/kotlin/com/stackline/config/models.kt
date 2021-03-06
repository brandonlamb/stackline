package com.stackline.config

data class DefaultConfig(
  val apiProductRead: String,
  val apiProductWrite: String,
  val ctxPoolSize: Int,
  val dataSrcFilename: String,
  val dataDstFilename: String,
  val dataUrl: String,
  val downloadFile: Boolean,
  val esHost: String,
  val esIndex: String,
  val esPort: Int,
  val esTypeProduct: String
)
