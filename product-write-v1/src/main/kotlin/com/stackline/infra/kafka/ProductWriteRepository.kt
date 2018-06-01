package com.stackline.infra.kafka

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import com.jsoniter.output.JsonStream
import com.stackline.infra.logging.Logger
import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Primary
import io.reactivex.Single
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.concurrent.TimeUnit.SECONDS
import javax.annotation.PreDestroy
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.text.Charsets.UTF_8

@Singleton
@Primary
class ProductWriteRepository @Inject constructor(
  system: ActorSystem
) : ProductWriteRepository, Logger {
  private val producerSettings = ProducerSettings.create(system, StringSerializer(), ByteArraySerializer())
  private val producer = producerSettings.createKafkaProducer()
  private val topic = "create-product-v1"

  override fun create(product: Product): Single<Product> {
    return Single.just(product)
      .map {
        getLogger().info("product={}", it.id)
        it
      }
      .map {
        producer.send(ProducerRecord(topic, it.id, JsonStream.serialize(it).toByteArray(UTF_8)))
        it
      }
  }

  override fun update(product: Product): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun removeById(id: ProductId): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  @PreDestroy
  fun preDestroy() {
    producer.flush()
    producer.close(10, SECONDS)
  }
}
