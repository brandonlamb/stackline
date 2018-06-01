package com.stackline.infra.logging

import org.slf4j.LoggerFactory

interface Logger {
  fun getLogger() = LoggerFactory.getLogger("com.stackline")
}
