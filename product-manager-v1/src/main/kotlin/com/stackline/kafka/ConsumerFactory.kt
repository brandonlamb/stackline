package com.stackline.kafka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import javax.inject.Inject

@Context
class ConsumerFactory @Inject constructor(
  system: ActorSystem,
  materializer: ActorMaterializer,
  @Value("\${stackline.kafka.topic}") createTopic: String
) {
  init {
    system.actorOf(ProductConsumer.props(system, materializer, createTopic))
  }
}
