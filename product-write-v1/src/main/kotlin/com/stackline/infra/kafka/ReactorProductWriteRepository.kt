package com.stackline.infra.kafka

import com.jsoniter.output.JsonStream
import com.stackline.infra.logging.Logger
import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Primary
import io.reactivex.Single
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringSerializer
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import java.util.Properties
import javax.inject.Singleton
import kotlin.text.Charsets.UTF_8

@Singleton
@Primary
class ReactorProductWriteRepository : ProductWriteRepository, Logger {
  private val props = Properties()
  private val senderOptions: SenderOptions<String, ByteArray>
  private val sender: KafkaSender<String, ByteArray>
  private val topic = "create-product-v1"

  init {
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "product-write-v1")
    props.setProperty(ProducerConfig.ACKS_CONFIG, "all")
    props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.toGenericString())
    props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer::class.java.toGenericString())

    senderOptions = SenderOptions.create(props)
    sender = KafkaSender.create(senderOptions)
  }

  override fun create(product: Product): Single<Product> {
    return Single.just(product)
      .map {
        sender.send<Product> {
          Mono
            .just(product)
            .map {
              SenderRecord.create(ProducerRecord(topic, product.id, JsonStream.serialize(product).toByteArray(UTF_8)), product.id)
            }
        }
          .doOnError { getLogger().error("Send failed", it) }
          .subscribe()
        it
      }
  }

  override fun update(product: Product): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun removeById(id: ProductId): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
