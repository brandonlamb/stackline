package com.stackline.infra.rest

import com.stackline.product.domain.Category
import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.Seller
import com.stackline.product.domain.api.ProductReadRepository
import io.micronaut.context.annotation.Primary
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class ProductReadRepository @Inject constructor(
  @Client("\${stackline.api.product-read-v1}") private val client: RxHttpClient
) : ProductReadRepository {
  override fun findById(id: Single<ProductId>): Single<Product> {
    //    val x: Flowable<String> = client.retrieve(HttpRequest.GET("/test", String::class.java))
    return id.map { Product(it, "the product", Seller(456, "abc"), Category(123, "xyz")) }
  }
}
