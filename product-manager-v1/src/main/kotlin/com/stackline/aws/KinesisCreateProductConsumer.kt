package com.stackline.aws
/*
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.kinesis.ShardSettings
import akka.stream.alpakka.kinesis.javadsl.KinesisSource
import akka.stream.javadsl.Sink
import com.amazonaws.services.kinesis.AmazonKinesisAsync
import com.amazonaws.services.kinesis.model.ShardIteratorType
import com.jsoniter.JsonIterator
import com.stackline.product.domain.Product
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

//@Context
class KinesisCreateProductConsumer @Inject constructor(
  kinesisClient: AmazonKinesisAsync,
  system: ActorSystem,
  materializer: ActorMaterializer,
//  @Value("\${stackline.aws.kinesis.create-product}") private val streamName: String
  @Value("\${stackline.aws.kinesis.create-product-stream-name}") private val streamName: String
) {
  private val logger = LoggerFactory.getLogger("com.stackline")

  private val settings = ShardSettings.create(streamName, "shardId-000000000000")
    .withShardIteratorType(ShardIteratorType.LATEST)
    .withRefreshInterval(1L, TimeUnit.SECONDS)
    .withLimit(500)

  private val source = KinesisSource.basic(settings, kinesisClient)
    .map { JsonIterator.deserialize(it.data.array(), Product::class.java) }
    .map {
      logger.info("{}", it)
      it
    }
    .runWith(Sink.ignore(), materializer)

  init {
    system.registerOnTermination(kinesisClient::shutdown)
    logger.info("KinesisCreateProductConsumer started for $streamName")
  }
}
*/
