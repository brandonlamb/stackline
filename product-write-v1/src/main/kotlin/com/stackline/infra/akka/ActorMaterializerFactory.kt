package com.stackline.infra.akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class ActorMaterializerFactory {
  @Bean(preDestroy = "shutdown")
  @Singleton
  fun create(system: ActorSystem): ActorMaterializer = ActorMaterializer.create(system)
}
