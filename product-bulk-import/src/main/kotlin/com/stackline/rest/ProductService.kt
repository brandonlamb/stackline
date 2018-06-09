package com.stackline.rest

import com.jsoniter.output.JsonStream
import com.stackline.Product
import com.stackline.toProduct
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import kotlin.coroutines.experimental.CoroutineContext

class ProductService(
  private val ctx: CoroutineContext,
  private val client: OkHttpClient,
  private val source: String,
  private val writeUrl: String
) {
  private val json = MediaType.parse("application/json; charset=utf-8")

  /**
   * Read lines from the data file, for each line parse into a Product and POST to the createRestClient product api
   */
  suspend fun import() {
    File(source).useLines { lines ->
      lines.forEach { line ->
        runBlocking(ctx) {
          if (line.isEmpty() || line.isBlank()) {
            return@runBlocking
          }

          val product = line.toProduct()

          val job = async { create(product) }
          job.await()
        }
      }
    }
  }

  private fun create(product: Product) {
    val body = RequestBody.create(json, JsonStream.serialize(product))
    val request = Request.Builder().url(writeUrl).post(body).build()

    try {
      client.newCall(request).execute().use {
        println("${LocalDateTime.now()} - created ${product.id}")
      }
    } catch (e: IOException) {
      println("${LocalDateTime.now()} - error ${e.message}")
    }
  }
}
