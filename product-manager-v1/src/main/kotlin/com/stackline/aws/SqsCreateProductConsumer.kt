package com.stackline.aws
/*
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.sqs.Attribute
import akka.stream.alpakka.sqs.MessageAttributeName
import akka.stream.alpakka.sqs.SqsSourceSettings
import akka.stream.alpakka.sqs.javadsl.SqsSource
import akka.stream.javadsl.Sink
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.model.Message
import com.jsoniter.JsonIterator
import com.jsoniter.spi.JsonException
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Value
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.inject.Inject

//@Context
class SqsCreateProductConsumer @Inject constructor(
  private val sqsClient: AmazonSQSAsync,
  private val system: ActorSystem,
  materializer: ActorMaterializer,
  @Value("\${stackline.aws.sqs.create-product}") private val sqsEndpoint: String
) {
  private val logger = LoggerFactory.getLogger("com.stackline")

  private val settings = SqsSourceSettings.Defaults()
    .withWaitTimeSeconds(20)
    .withMaxBufferSize(100)
    .withMaxBatchSize(10)
    .withAttributes(Attribute.senderId(), Attribute.sentTimestamp())
    .withMessageAttributes(MessageAttributeName.create("bar.*"))
    .withCloseOnEmptyReceive()

  private val cs = SqsSource.create(sqsEndpoint, settings, sqsClient)
    .map(Message::getBody)
    .map {
      try {
        val product = JsonIterator.deserialize(it, Product::class.java)
        logger.info("Received $product")
        product
      } catch (e: JsonException) {
        logger.error(e.message)
      }
    }
    .runWith(Sink.head(), materializer)
    .toCompletableFuture()
    .get()

  @PostConstruct
  fun postConstruct() {
    system.registerOnTermination(sqsClient::shutdown)
    logger.info("SqsCreateProductConsumer started for $sqsEndpoint")
  }
}
*/
