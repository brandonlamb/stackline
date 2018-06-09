package com.stackline.infra.es

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import javax.inject.Singleton

@Factory
class ClientFactory {
  @Bean(preDestroy = "close")
  @Singleton
  fun create(
    @Value("\${stackline.elasticsearch.host}") host: String,
    @Value("\${stackline.elasticsearch.port}") port: Int
  ): RestClient = RestClient.builder(HttpHost(host, port)).build()
}
