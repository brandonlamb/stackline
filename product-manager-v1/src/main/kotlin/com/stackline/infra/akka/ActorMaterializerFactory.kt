package com.stackline.infra.akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.annotation.PreDestroy
import javax.inject.Inject
import javax.inject.Singleton

@Factory
class ActorMaterializerFactory @Inject constructor(system: ActorSystem) {
  private val actorMaterializer = ActorMaterializer.create(system)

  @Bean
  @Singleton
  fun create(): ActorMaterializer = actorMaterializer

  @PreDestroy
  fun preDestroy() {
    actorMaterializer.shutdown()
  }
}
