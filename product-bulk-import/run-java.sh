#!/bin/sh

java -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xmx64m -Dstackline.downloadFile=false -jar build/libs/product-bulk-import-0.1.jar
