package com.stackline.kafka

import akka.actor.ActorSystem
import akka.kafka.ConsumerMessage.emptyCommittableOffsetBatch
import akka.kafka.Subscriptions.topics
import akka.kafka.javadsl.Consumer
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Sink
import com.jsoniter.JsonIterator
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Context
class ProductConsumer @Inject constructor(
  system: ActorSystem,
  materializer: ActorMaterializer,
  @Value("\${stackline.kafka.topic}") private val topic: String
) : BaseConsumer(system) {
  private val logger = LoggerFactory.getLogger("com.stackline")

//  private val consumer1 = Consumer
//    .committablePartitionedSource(consumerSettings, topics(topic))
//    .map { pair ->
//      pair
//        .second()
//        .map {
//          try {
//            println(JsonIterator.deserialize(it.record().value(), Product::class.java))
//          } catch (e: Exception) {
//            logger.error(e.message)
//          }
//          pair
//        }
//        .toMat(Sink.ignore(), Keep.both())
//        .run(materializer)
//    }
//    .mapAsyncUnordered(maxPartitions, { it.second() })
//    .to(Sink.ignore())
//    .run(materializer)

  private val consumer2 = Consumer
    .committablePartitionedSource(consumerSettings, topics(topic))
    .flatMapMerge(maxPartitions, { it.second() })
    .map {
      try {
        println(JsonIterator.deserialize(it.record().value(), Product::class.java))
      } catch (e: Exception) {
        logger.error(e.message)
      }
      it
    }
    .map { it.committableOffset() }
    .batch(100, { emptyCommittableOffsetBatch().updated(it) }, { batch, elem -> batch.updated(elem) })
    .mapAsync(8, { it.commitJavadsl() })
    .runWith(Sink.ignore(), materializer)
}

