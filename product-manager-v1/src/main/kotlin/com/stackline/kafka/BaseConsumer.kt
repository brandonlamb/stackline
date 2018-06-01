package com.stackline.kafka

import akka.actor.ActorSystem
import akka.kafka.ConsumerSettings
import akka.kafka.Subscriptions
import akka.kafka.javadsl.Consumer
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Keep
import akka.stream.javadsl.Sink
import com.jsoniter.JsonIterator
import com.typesafe.config.Config
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import javax.inject.Inject

abstract class BaseConsumer @Inject constructor(system: ActorSystem) {
  protected val maxPartitions = 100
  protected val consumerSettings = ConsumerSettings.create(system, StringDeserializer(), ByteArrayDeserializer())!!
}
