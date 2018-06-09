package com.stackline.kafka

import akka.actor.ActorSystem
import akka.actor.Props
import akka.kafka.ConsumerMessage.emptyCommittableOffsetBatch
import akka.kafka.Subscriptions.topics
import akka.kafka.javadsl.Consumer
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Sink
import com.jsoniter.JsonIterator
import org.slf4j.LoggerFactory

class ProductConsumer(
  system: ActorSystem,
  materializer: ActorMaterializer,
  topic: String
) : BaseConsumer(system) {
  private val logger = LoggerFactory.getLogger("com.stackline")

  private val consumer = Consumer
    .committablePartitionedSource(consumerSettings, topics(topic))
    .flatMapMerge(maxPartitions, { it.second() })
    .map {
      try {
        val product = JsonIterator.deserialize(it.record().value(), Product::class.java)
        logger.info("event=ProductCreated id={}", product.id)
      } catch (e: Exception) {
        logger.error("event=CreateProductFailed message={}", e.message)
      }
      it
    }
    .map { it.committableOffset() }
    .batch(100, { emptyCommittableOffsetBatch().updated(it) }, { batch, elem -> batch.updated(elem) })
    .mapAsync(4, { it.commitJavadsl() })
    .runWith(Sink.ignore(), materializer)

  override fun createReceive(): Receive = receiveBuilder().matchAny { println(it) }.build()

  companion object {
    fun props(system: ActorSystem, materializer: ActorMaterializer, topic: String): Props =
      Props.create(ProductConsumer::class.java, system, materializer, topic)
  }
}
