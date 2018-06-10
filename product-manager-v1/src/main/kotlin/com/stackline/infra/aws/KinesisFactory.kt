package com.stackline.infra.aws

/*
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.regions.Regions.US_WEST_2
import com.amazonaws.services.kinesis.AmazonKinesisAsync
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import java.util.concurrent.Executors
import javax.annotation.PreDestroy
import javax.inject.Inject
import javax.inject.Singleton

@Factory
class KinesisFactory @Inject constructor(credentials: AWSCredentials) {
  private val client = AmazonKinesisAsyncClientBuilder
    .standard()
    .withCredentials(AWSStaticCredentialsProvider(credentials))
    .withExecutorFactory { Executors.newFixedThreadPool(10) }
    .withRegion(US_WEST_2)
    .build()

  @Bean
  @Singleton
  fun create(): AmazonKinesisAsync = client

  @PreDestroy
  fun preDestroy() {
    client.shutdown()
  }
}
*/
