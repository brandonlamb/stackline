package com.stackline.infra.akka

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ConfigFactory {
  private val config: Config = ConfigFactory
    .load(javaClass.classLoader, "reference.conf")
    .withFallback(ConfigFactory.load(javaClass.classLoader, "application.conf"))

  @Bean
  @Singleton
  fun create(): Config = config
}
