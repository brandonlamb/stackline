package com.stackline.infra.aws
/*
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.kinesis.KinesisFlowSettings
import akka.stream.alpakka.kinesis.ShardSettings
import akka.stream.alpakka.kinesis.javadsl.KinesisFlow
import akka.stream.alpakka.kinesis.javadsl.KinesisSink
import akka.stream.alpakka.kinesis.javadsl.KinesisSource
import com.amazonaws.AmazonClientException
import com.amazonaws.services.kinesis.AmazonKinesisAsync
import com.amazonaws.services.kinesis.model.PutRecordRequest
import com.amazonaws.services.kinesis.model.ShardIteratorType
import com.jsoniter.output.JsonStream
import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.api.AkkaProductWriteRepository
import io.micronaut.context.annotation.Value
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AkkaProductWriteRepository @Inject constructor(
  system: ActorSystem,
  private val materializer: ActorMaterializer,
  private val kinesisClient: AmazonKinesisAsync,
//  private val sqsClient: AmazonSQSAsync,
  @Value("\${stackline.aws.kinesis.create-product-stream-name}") private val streamName: String
//  @Value("\${stackline.aws.sqs.create-product}") private val sqsEndpoint: String
) : AkkaProductWriteRepository {
  private val logger = LoggerFactory.getLogger("com.stackline")

  private val settings = ShardSettings.create(streamName, "shardId-000000000000")
    .withShardIteratorType(ShardIteratorType.LATEST)
    .withRefreshInterval(1L, TimeUnit.SECONDS)
    .withLimit(500)

  private val source = KinesisSource.basic(settings, kinesisClient)

  private val flowSettings = KinesisFlowSettings.create()
    .withParallelism(1)
    .withMaxBatchSize(500)
    .withMaxRecordsPerSecond(1_000)
    .withMaxBytesPerSecond(1_000_000)
    .withMaxRecordsPerSecond(5)
    .withBackoffStrategyExponential()
    .withRetryInitialTimeout(100L, TimeUnit.MILLISECONDS)

  private val flow = KinesisFlow.apply(streamName, flowSettings, kinesisClient)
  private val sink = KinesisSink.apply(streamName, flowSettings, kinesisClient)

//  private val settings = SqsSourceSettings.Defaults()
//    .withWaitTimeSeconds(20)
//    .withMaxBufferSize(100)
//    .withMaxBatchSize(10)
//    .withAttributes(Attribute.senderId(), Attribute.sentTimestamp())
//    .withMessageAttributes(MessageAttributeName.create("bar.*"))
//    .withCloseOnEmptyReceive()

  init {
    system.registerOnTermination(kinesisClient::shutdown)
//    system.registerOnTermination(sqsClient::shutdown)
  }

  override fun create(product: Product): Single<Product> = Single.just(product)
    .subscribeOn(Schedulers.io())
    .map { product ->
      logger.info("Publishing ${product.id}")

      val record = PutRecordRequest()
        .withStreamName(streamName)
        .withPartitionKey(product.id)
        .withData(ByteBuffer.wrap(JsonStream.serialize(product).toByteArray(Charsets.UTF_8)))

      try {
        kinesisClient.putRecord(record)
      } catch (e: AmazonClientException) {
        logger.warn(e.message)
      }

//      Source
//        .single(SendMessageRequest().withMessageBody(JsonStream.serialize(it)))
//        .runWith(SqsSink.messageSink(sqsEndpoint, sqsClient), materializer)
//        .toCompletableFuture()
//        .get()

      product
    }

  override fun update(product: Product): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun removeById(id: ProductId): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
*/
