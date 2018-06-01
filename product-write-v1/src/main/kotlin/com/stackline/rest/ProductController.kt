package com.stackline.rest

import com.stackline.product.domain.CreateProduct
import com.stackline.product.service.CreateProductHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Put
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Controller("/v1/products")
@Produces(APPLICATION_JSON)
class ProductController @Inject constructor(
  private val createProductHandler: CreateProductHandler
) {
  @Post("/")
  fun create(@Body product: Single<Product>): Single<MutableHttpResponse<Void>> = product.subscribeOn(Schedulers.io())
    .flatMap {
      createProductHandler.handle(CreateProduct(it.toDomain())).map { HttpResponse.accepted<Void>() }
    }

  @Put("/{id}")
  fun update(@Body product: Single<Product>): Single<HttpResponse<Void>> = Single.just(HttpResponse.accepted())

  @Delete("/{id}")
  fun delete(id: Single<String>): Single<HttpResponse<Void>> = Single.just(HttpResponse.noContent())
}
