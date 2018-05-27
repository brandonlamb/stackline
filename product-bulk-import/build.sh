#!/bin/sh

./gradlew clean build
java -jar build/libs/product-bulk-import-0.1.jar

