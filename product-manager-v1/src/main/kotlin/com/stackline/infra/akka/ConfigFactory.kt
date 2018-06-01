package com.stackline.infra.akka

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory.load
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ConfigFactory {
  @Bean
  @Singleton
  fun create(): Config =
    load(javaClass.classLoader, "reference.conf").withFallback(load(javaClass.classLoader, "application.conf"))
}
