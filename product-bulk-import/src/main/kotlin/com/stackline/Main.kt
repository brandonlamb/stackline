package com.stackline

import com.stackline.config.Config
import com.stackline.rest.Downloader
import com.stackline.rest.OkHttpClientFactory
import com.stackline.rest.ProductService
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.runBlocking
import java.net.ConnectException
import java.time.LocalDateTime

/**
 * This is a very hacky, quick and dirty solution to bootstrap various services
 */
object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    println("Bulk import started ${LocalDateTime.now()}")

    val config = Config.create()

    val ctx = newFixedThreadPoolContext(config.ctxPoolSize, "product-ctx")
    val client = OkHttpClientFactory.create(config)
//    val esClient = ElasticSearchFactory.create(config)
    val downloader = Downloader()
    val productService = ProductService(
      ctx,
      client,
      config.dataDstFilename,
      config.apiProductWrite
    )
//    val indexHandler = IndexHandler(config, esClient)
//    indexHandler.handle(CreateIndex(config.esIndex))

    if (config.downloadFile) {
      downloader.download(client, config.dataUrl, config.dataSrcFilename)
      downloader.decompress(config.dataSrcFilename, config.dataDstFilename)
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
