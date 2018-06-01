package com.stackline.infra.kafka

import com.stackline.product.domain.Product
import com.stackline.product.domain.ProductId
import com.stackline.product.domain.api.ProductWriteRepository
import io.micronaut.context.annotation.Primary
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
@Primary
class ProductWriteRepository : ProductWriteRepository {
  override fun create(product: Product): Single<Product> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun update(product: Product): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun removeById(id: ProductId): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
