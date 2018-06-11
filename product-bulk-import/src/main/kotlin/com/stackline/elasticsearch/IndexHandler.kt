package com.stackline.elasticsearch
/*
import com.stackline.config.DefaultConfig
import org.elasticsearch.action.admin.indices.alias.Alias
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.xcontent.XContentType
import java.io.File
import java.util.UUID

class IndexHandler(
  private val config: DefaultConfig,
  private val client: RestHighLevelClient
) {
  fun handle(cmd: CreateIndex) {
    createIndex(cmd.index)
  }

  private fun createIndex(index: String) {
    val request = CreateIndexRequest(index + UUID.randomUUID())
      .settings(Settings.builder()
        .put("index.number_of_shards", 3)
        .put("index.number_of_replicas", 2)
      )
      .mapping("product", productType(), XContentType.JSON)
      .alias(Alias(index))

    val response = client.indices().create(request)
  }

  private fun productType(): String = File(config.esTypeProduct).readText(Charsets.UTF_8)
}
*/
