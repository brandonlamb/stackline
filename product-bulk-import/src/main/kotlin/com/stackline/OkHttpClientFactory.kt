package com.stackline

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MINUTES

object OkHttpClientFactory {
  fun create(): OkHttpClient {
    System.setProperty("http.maxConnections", "500")

    return OkHttpClient
      .Builder()
      .connectionPool(ConnectionPool(Config.CTX_POOL_SIZE, 1, MINUTES))
      .retryOnConnectionFailure(true)
      .addInterceptor(RetryInterceptor())
      .readTimeout(90, TimeUnit.SECONDS)
//      .sslSocketFactory(trustAllSslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
      .hostnameVerifier({ _, _ -> true })
      .build()
  }
}
