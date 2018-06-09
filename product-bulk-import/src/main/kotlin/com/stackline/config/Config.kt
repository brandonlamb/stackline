package com.stackline.config

import com.typesafe.config.ConfigFactory

object Config {
  fun create(): DefaultConfig {
    val config = ConfigFactory.load().getConfig("stackline")

    return DefaultConfig(
      apiProductRead = config.getString("api.product-read"),
      apiProductWrite = config.getString("api.product-write"),
      ctxPoolSize = config.getInt("ctx-pool-size"),
      dataFilename = config.getString("data.filename"),
      dataUrl = config.getString("data.url"),
      downloadFile = config.getBoolean("download-file"),
      esHost = config.getString("es.host"),
      esIndex = config.getString("es.index"),
      esPort = config.getInt("es.port"),
      esTypeProduct = config.getString("es.product-type-mapping")
    )
  }
}
