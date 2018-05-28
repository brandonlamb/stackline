package com.stackline

import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.runBlocking
import java.net.ConnectException
import java.time.LocalDateTime

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    println("Bulk import started ${LocalDateTime.now()}")

    val downloadFlag = System.getProperty("stackline.downloadFile", "true")!!.toBoolean()

    val ctx = newFixedThreadPoolContext(Config.CTX_POOL_SIZE, "product-ctx")
    val client = OkHttpClientFactory.create()
    val downloader = Downloader()
    val productService = ProductService(
      ctx,
      client,
      Config.DATA_FILE,
      Config.PRODUCT_WRITE_URL
    )

    if (downloadFlag) {
      downloader.download(client, Config.URL, Config.DOWNLOAD_FILE)
      downloader.decompress(Config.DOWNLOAD_FILE, Config.DATA_FILE)
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
