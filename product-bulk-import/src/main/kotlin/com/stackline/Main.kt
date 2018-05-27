package com.stackline

import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.zip.GZIPInputStream

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    println("Bulk import started ${LocalDateTime.now()}")

    val client = createClient()

    download(client, Config.URL, Config.DOWNLOAD_FILE)
//    download(Config.URL, Config.DOWNLOAD_FILE)
    decompress(Config.DOWNLOAD_FILE, Config.DATA_FILE)

    println("Bulk import finished ${LocalDateTime.now()}")
  }
}

object Config {
  const val URL = "https://s3-us-west-2.amazonaws.com/stackline-public/sample_product_data.tsv.gz"
  const val DOWNLOAD_FILE = "data.tdf.gz"
  const val DATA_FILE = "data.tdf"
}

fun createClient(): OkHttpClient = OkHttpClient()

fun download(client: OkHttpClient, source: String, destination: String) {
  val request = Request.Builder().url(source).build()
  val response = client.newCall(request).execute()

  response.body()?.byteStream().use { fis ->
    FileOutputStream(destination).use { fos ->
      val buffer = ByteArray(1024)
      var len = fis?.read(buffer) ?: -1

      while (len != -1) {
        fos.write(buffer, 0, len)
        len = fis?.read(buffer) ?: -1
      }
    }
  }
}

/**
 * Download the gzip source file from S3
 */
fun download(source: String, filename: String) {
  val client = HttpClientBuilder.create().build()
  val request = HttpGet(source)
  val response = client.execute(request)
  val entity = response.entity

  if (entity != null) {
    val fos = FileOutputStream(filename)
    entity.writeTo(fos)
    fos.close()
  }
}

/**
 * Decompress the gzipped file
 */
fun decompress(source: String, destination: String) {
  FileInputStream(source).use { fis ->
    GZIPInputStream(fis).use { gis ->
      FileOutputStream(destination).use { fos ->
        val buffer = ByteArray(1024)
        var len = gis.read(buffer)

        while (len != -1) {
          fos.write(buffer, 0, len)
          len = gis.read(buffer)
        }
      }
    }
  }
}

/**
 * Read lines from the data file, for each line parse into a Product and POST to the create product api
 */
fun readLines(source: String) {
  File(source).useLines {
    it.forEach { line ->
      val product = line.toProduct()
    }
  }
}

/**
 * Extension function to convert a line from the flat-file source into a product
 */
fun String.toProduct(): Product {
  // Parse the tab-delimited value
  val p = split("\t")

  return Product(
    productId = p[0],
    description = p[1],
    sellerId = p[2].toLong(),
    sellerName = p[3],
    categoryId = p[4].toLong(),
    categoryName = p[5]
  )
}

data class Product(
  val productId: String,
  val description: String,
  val sellerId: Long,
  val sellerName: String,
  val categoryId: Long,
  val categoryName: String
)
