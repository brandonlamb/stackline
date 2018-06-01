package com.stackline.infra.aws
/*
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.sqs.Attribute
import akka.stream.alpakka.sqs.MessageAttributeName
import akka.stream.alpakka.sqs.SqsSourceSettings
import akka.stream.alpakka.sqs.javadsl.SqsSink
import akka.stream.javadsl.Source
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.jsoniter.output.JsonStream
import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Value
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class SqsProductWrite @Inject constructor(
  system: ActorSystem,
  private val materializer: ActorMaterializer,
  private val sqsClient: AmazonSQSAsync,
  @Value("\${stackline.aws.sqs.create-product}") private val sqsEndpoint: String
) : ProductWriteRepository {
  private val logger = LoggerFactory.getLogger("com.stackline")

  private val settings = SqsSourceSettings.Defaults()
//    .withWaitTimeSeconds(5)
//    .withMaxBufferSize(100)
//    .withMaxBatchSize(10)
//    .withAttributes(Attribute.senderId(), Attribute.sentTimestamp())
//    .withMessageAttributes(MessageAttributeName.create("bar.*"))
//    .withCloseOnEmptyReceive()

  init {
    system.registerOnTermination(sqsClient::shutdown)
  }

  override fun create(product: Product): Single<Product> = Single.just(product)
    .subscribeOn(Schedulers.io())
    .map { p ->
      logger.info("Publishing ${p.id}")

      Source
        .single(SendMessageRequest().withMessageBody(JsonStream.serialize(p)))
        .runWith(SqsSink.messageSink(sqsEndpoint, sqsClient), materializer)
        .toCompletableFuture()
        .get()

      p
    }

  override fun update(product: Product): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun removeById(id: ProductId): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
*/
