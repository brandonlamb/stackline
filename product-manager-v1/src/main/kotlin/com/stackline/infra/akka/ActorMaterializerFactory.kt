package com.stackline.infra.akka

import akka.actor.ActorSystem
import akka.japi.function.Function
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializerSettings
import akka.stream.Supervision
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.apache.kafka.common.KafkaException
import org.apache.kafka.common.errors.WakeupException
import org.slf4j.LoggerFactory
import java.net.ProtocolException
import javax.inject.Singleton

@Factory
class ActorMaterializerFactory {
  @Bean(preDestroy = "shutdown")
  @Singleton
  fun create(system: ActorSystem): ActorMaterializer = ActorMaterializer.create(
    ActorMaterializerSettings
      .create(system)
      .withSupervisionStrategy(Function {
        LoggerFactory.getLogger("com.stackline").info(it.toString())

        when (it) {
          is WakeupException -> Supervision.resume()
          is KafkaException -> Supervision.resume()
          is ProtocolException -> Supervision.resume()
          else -> Supervision.restart()
        }
      }),
    system
  )
}
