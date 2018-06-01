package com.stackline.kafka

import akka.actor.ActorSystem
import akka.kafka.ConsumerSettings
import akka.kafka.Subscriptions
import akka.kafka.javadsl.Consumer
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Keep
import akka.stream.javadsl.Sink
import com.jsoniter.JsonIterator
import com.stackline.product.domain.Product
import com.stackline.product.domain.api.ProductService
import com.typesafe.config.Config
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import javax.inject.Inject

open class BaseConsumer @Inject constructor(
  config: Config,
  system: ActorSystem,
  protected val materializer: ActorMaterializer
) {
//  private val config = system.settings().config()!!
  protected val maxPartitions = 100

  protected val consumerSettings = ConsumerSettings
    .create(system, StringDeserializer(), ByteArrayDeserializer())!!
//    .withBootstrapServers("localhost:9092")
//    .withGroupId("group1")
//    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")!!

//  protected val consumerSettingsWithAutoCommit = consumerSettings
//    .withProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
//    .withProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000")

//  protected val producerSettings = ProducerSettings
//    .create(system, StringSerializer(), ByteArraySerializer())
//    .withBootstrapServers("localhost:9092")

  init {
    val x = config.getConfig("akka.kafka.consumer.kafka-clients")
    println("BASECONSUMER: " + x.toString())
  }
}

@Context
class ProductConsumer @Inject constructor(
  config: Config,
  system: ActorSystem,
  materializer: ActorMaterializer,
  @Value("\${stackline.kafka.topic}") private val topic: String
//  private val productService: ProductService
) : BaseConsumer(config, system, materializer) {
  private val control = Consumer
    .committablePartitionedSource(consumerSettings, Subscriptions.topics(topic))
    .map { pair ->
      pair
        .second()
        .map {
          println(JsonIterator.deserialize(it.record().value(), Product::class.java))
          pair
        }
        .toMat(Sink.ignore(), Keep.both())
        .run(materializer)
    }
    .mapAsyncUnordered(maxPartitions, { it.second() })
    .to(Sink.ignore())
    .run(materializer)

//      .map { pair ->
//        pair
//          .second()
//          .via(business())
//          .mapAsync(1, {it.committableOffset().commitJavadsl() })
//          .runWith(Sink.ignore(), materializer)
//      }
//      .mapAsyncUnordered(maxPartitions, { it })
//      .toMat(Sink.ignore(), Keep.both())
////      .mapMaterializedValue(Consumer::create)
//      .run(materializer)

//    control.drainAndShutdown(ec)
}
