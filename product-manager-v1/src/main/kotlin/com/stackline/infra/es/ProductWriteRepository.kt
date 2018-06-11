package com.stackline.infra.es

import akka.stream.ActorMaterializer
import akka.stream.alpakka.elasticsearch.IncomingMessage
import akka.stream.alpakka.elasticsearch.javadsl.ElasticsearchSink
import akka.stream.alpakka.elasticsearch.javadsl.ElasticsearchSinkSettings
import akka.stream.javadsl.Source
import com.fasterxml.jackson.databind.ObjectMapper
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Value
import io.reactivex.Single
import org.elasticsearch.client.RestClient
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import com.stackline.product.domain.Product as DomainProduct

@Singleton
@Named("es")
class ProductWriteRepository @Inject constructor(
  private val client: RestClient,
  private val mapper: ObjectMapper,
  private val materializer: ActorMaterializer,
  @Value("\${stackline.elasticsearch.index}") private val index: String,
  @Value("\${stackline.elasticsearch.type}") private val type: String
) : ProductWriteRepository {
  private val logger = LoggerFactory.getLogger("com.stackline")
  private val sinkSettings = ElasticsearchSinkSettings()

  override fun create(product: DomainProduct): Single<DomainProduct> = Single.fromFuture(Source.single(product)
    .map { IncomingMessage.create(it.id, product.toEs()) }
    .runWith(ElasticsearchSink.create(index, type, sinkSettings, client, mapper), materializer).toCompletableFuture())
    .map { logger.info("type=IndexProduct product={}", product) }
    .map { product }
}
