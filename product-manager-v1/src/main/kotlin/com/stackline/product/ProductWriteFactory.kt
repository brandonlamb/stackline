package com.stackline.product

import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Named
import javax.inject.Singleton

@Factory
class ProductWriteFactory {
  @Bean
  @Singleton
  fun create(
    @Named("es") r1: ProductWriteRepository
  ): List<ProductWriteRepository> = listOf(r1)
}
