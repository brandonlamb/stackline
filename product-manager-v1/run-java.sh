#!/bin/sh

#java -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xms128m -Xmx64m -jar build/libs/product-manager-v1-0.1-all.jar
java -Dconfig.resource=akka.conf -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xmx128m -jar build/libs/product-manager-v1-0.1-all.jar

#java -Dconfig.resource=akka.conf -XX:+UseNUMA -XX:+UseParallelGC -XX:+AggressiveOpts -Xmx128m -jar target/product-manager-v1-0.1.jar
