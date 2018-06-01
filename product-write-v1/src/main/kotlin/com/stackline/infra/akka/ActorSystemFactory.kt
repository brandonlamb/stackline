package com.stackline.infra.akka

import akka.actor.ActorSystem
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ActorSystemFactory {
  private val system = ActorSystem.create()

  @Bean
  @Singleton
  fun create(): ActorSystem = system
}
