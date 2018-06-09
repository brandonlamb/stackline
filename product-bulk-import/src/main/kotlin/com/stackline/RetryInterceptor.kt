package com.stackline

import okhttp3.Interceptor
import okhttp3.Response
import java.time.Duration
import java.time.temporal.ChronoUnit

class RetryInterceptor : Interceptor {
  private val maxRetries = 5
  private val waitTime = Duration.of(500, ChronoUnit.MILLIS)

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    var response: Response
    var count = 0

    do {
      count.takeIf { it > 0 }?.let {
        Thread.sleep(waitTime.toMillis())
      }

      response = chain.proceed(request)
    } while (!response.isSuccessful && response.code() != 400 && count++ < maxRetries)

    return response
  }
}
