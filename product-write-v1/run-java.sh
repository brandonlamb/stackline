#!/bin/sh

java -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xmx128m -jar build/libs/product-write-v1-0.1-all.jar
#java -Dcom.amazonaws.sdk.disableCbor=true -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xmx64m -jar build/libs/product-write-v1-0.1-all.jar
