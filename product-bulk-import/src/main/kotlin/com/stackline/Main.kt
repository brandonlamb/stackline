package com.stackline

import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.runBlocking
import java.net.ConnectException
import java.time.LocalDateTime

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    println("Bulk import started ${LocalDateTime.now()}")

    val config = Config.create()

    val ctx = newFixedThreadPoolContext(config.ctxPoolSize, "product-ctx")
    val client = OkHttpClientFactory.create()
    val esClient = ElasticSearchFactory.create(config)
    val downloader = Downloader()
    val productService = ProductService(
      ctx,
      client,
      config.dataFilename,
      config.apiProductWrite
    )

    if (config.downloadFile) {
      downloader.download(client, config.apiProductWrite, config.dataFilename)
      downloader.decompress(config.dataUrl, config.dataFilename)
    }

    runBlocking(ctx) {
      try {
        productService.import()
      } catch (e: ConnectException) {
        println(e.message)
      }
    }

    client.connectionPool().evictAll()
    ctx.close()

    println("Bulk import finished ${LocalDateTime.now()}")
  }
}
