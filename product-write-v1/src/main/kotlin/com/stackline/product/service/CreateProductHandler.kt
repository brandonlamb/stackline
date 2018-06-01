package com.stackline.product.service

import com.stackline.product.domain.CreateProduct
import com.stackline.product.domain.Product
import com.stackline.product.domain.api.ProductReadRepository
import com.stackline.product.domain.api.ProductService
import io.micronaut.context.annotation.Primary
import io.reactivex.Single
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class CreateProductHandler @Inject constructor(
  private val productReadRepository: ProductReadRepository,
  private val productService: ProductService
) {
  private val logger = LoggerFactory.getLogger(CreateProductHandler::class.java)
//  private val validator = Validation.buildDefaultValidatorFactory().validator

  fun handle(cmd: CreateProduct): Single<Product> = Single.just(cmd.product)
//    .map { it.also { validator.validate(it) } }
    .flatMap { productService.create(it) }
//    .map {
//      logger.info("Created ${it.id}")
//      it
//    }
}
