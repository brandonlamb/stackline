package com.stackline.infra.akka

import akka.actor.ActorSystem
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.sslconfig.util.ConfigLoader
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.SECONDS
import javax.annotation.PreDestroy
import javax.inject.Inject
import javax.inject.Singleton

@Factory
class ActorSystemFactory @Inject constructor(config: Config) {
  private val system = ActorSystem.create("default", config)
  private val es = Executors.newFixedThreadPool(8)

  @Bean
  @Singleton
  fun create(): ActorSystem = system

  @PreDestroy
  fun preDestroy() {
    es.awaitTermination(30, SECONDS)
    system.terminate()
  }
}
