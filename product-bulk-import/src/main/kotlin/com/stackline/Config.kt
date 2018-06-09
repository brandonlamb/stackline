package com.stackline

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
      esPort = config.getInt("es.port")
    )
  }
}
