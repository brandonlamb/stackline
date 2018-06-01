package com.stackline.infra.config

import io.micronaut.http.client.HttpClientConfiguration
import io.micronaut.runtime.ApplicationConfiguration

class HttpConfig(conf: ApplicationConfiguration) : HttpClientConfiguration(conf) {
  init {

  }
}
