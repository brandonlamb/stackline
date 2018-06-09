package com.stackline.infra.es

import akka.stream.ActorMaterializer
import akka.stream.alpakka.elasticsearch.IncomingMessage
import akka.stream.alpakka.elasticsearch.javadsl.ElasticsearchSink
import akka.stream.alpakka.elasticsearch.javadsl.ElasticsearchSinkSettings
import akka.stream.javadsl.Source
import com.fasterxml.jackson.databind.ObjectMapper
import com.stackline.kafka.Product
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Value
import io.reactivex.Single
import org.elasticsearch.client.RestClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductWriteRepository @Inject constructor(
  private val client: RestClient,
  private val mapper: ObjectMapper,
  private val materializer: ActorMaterializer,
  @Value("\${stackline.elasticsearch.index}") private val index: String,
  @Value("\${stackline.elasticsearch.type}") private val type: String
) : ProductWriteRepository {
  private val sinkSettings = ElasticsearchSinkSettings()

  override fun create(product: Product): Single<Product> = Single.fromFuture(Source.single(product)
    .map { IncomingMessage.create(it.id, product) }
    .runWith(ElasticsearchSink.create(index, type, sinkSettings, client, mapper), materializer).toCompletableFuture()
  ).map { product }

  private fun test() {
    val lineItems = listOf(0, 1, 2)

    order.lineItems.forEachIndexed { index, lineItem ->
      mapper.createStaging(order, lineItem)
    }
  }
}
