package com.stackline.infra.akka

import akka.actor.ActorSystem
import com.typesafe.config.Config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ActorSystemFactory {
  @Bean(preDestroy = "terminate")
  @Singleton
  fun create(config: Config): ActorSystem = ActorSystem.create("default", config)
}
