#!/bin/sh

./gradlew clean build
java -Dstackline.downloadFile=false -jar build/libs/product-bulk-import-0.1.jar
