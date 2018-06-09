package com.stackline

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient

object ElasticSearchFactory {
  fun create(config: DefaultConfig): RestClient = RestClient.builder(HttpHost(config.esHost, config.esPort)).build()
}
