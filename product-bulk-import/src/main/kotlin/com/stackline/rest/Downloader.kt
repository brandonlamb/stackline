package com.stackline.rest

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream

class Downloader {
  /**
   * Download tab-separated file (gzipped)
   */
  fun download(client: OkHttpClient, source: String, destination: String) {
    println("Downloading $source")

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
   * Decompress the gzipped tab-separated file
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
}
