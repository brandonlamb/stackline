package com.stackline.kafka

import akka.actor.AbstractActor
import akka.actor.ActorSystem
import akka.kafka.ConsumerSettings
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import javax.inject.Inject

abstract class BaseConsumer @Inject constructor(system: ActorSystem) : AbstractActor() {
  protected val maxPartitions = 100
  protected val consumerSettings = ConsumerSettings.create(system, StringDeserializer(), ByteArrayDeserializer())!!
}
