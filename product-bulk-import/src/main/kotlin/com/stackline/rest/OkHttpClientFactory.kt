package com.stackline.rest

import com.stackline.config.DefaultConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MINUTES

object OkHttpClientFactory {
  fun create(config: DefaultConfig): OkHttpClient {
    System.setProperty("http.maxConnections", "500")

    return OkHttpClient
      .Builder()
      .connectionPool(ConnectionPool(config.ctxPoolSize, 1, MINUTES))
      .retryOnConnectionFailure(true)
      .addInterceptor(RetryInterceptor())
      .readTimeout(90, TimeUnit.SECONDS)
//      .sslSocketFactory(trustAllSslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
      .hostnameVerifier({ _, _ -> true })
      .build()
  }
}
