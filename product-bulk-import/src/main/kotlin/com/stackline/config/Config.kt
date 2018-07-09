package com.stackline.config

import com.typesafe.config.ConfigFactory

object Config {
  fun create(): DefaultConfig {
    val config = ConfigFactory.load("stackline.conf").getConfig("stackline")

    return DefaultConfig(
      apiProductRead = config.getString("api.product-read"),
      apiProductWrite = config.getString("api.product-write"),
      ctxPoolSize = config.getInt("ctx-pool-size"),
      dataSrcFilename = config.getString("data.src-filename"),
      dataDstFilename = config.getString("data.dst-filename"),
      dataUrl = config.getString("data.url"),
      downloadFile = config.getBoolean("download-file"),
      esHost = config.getString("es.host"),
      esIndex = config.getString("es.index"),
      esPort = config.getInt("es.port"),
      esTypeProduct = config.getString("es.product-type-mapping")
    )
  }
}

/*
object Config {
  fun create(): DefaultConfig = DefaultConfig(
    apiProductRead = "",
    apiProductWrite = "",
    ctxPoolSize = 20,
    dataSrcFilename = "",
    dataUrl = "",
    downloadFile = false,
    esHost = "localhost",
    esPort = 9200,
    esIndex = "stackline",
    esTypeProduct = "product"
  )
}
*/
