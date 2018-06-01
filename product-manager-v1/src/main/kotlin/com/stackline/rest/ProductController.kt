package com.stackline.rest

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Single
import javax.inject.Inject

@Controller("/")
@Produces(APPLICATION_JSON)
class ProductController @Inject constructor(
  @Client("\${stackline.source-url}")
  private val client: RxHttpClient
) {
  @Get("/readiness")
  fun readiness(): Single<HttpResponse<String>> {
    return Single.fromCallable { HttpResponse.ok("ok") }
  }
}
