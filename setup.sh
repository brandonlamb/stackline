#!/bin/sh

# Spin up the stack
docker-compose up -d

# Create the product topics (create, delete, update)
docker-compose exec kafka kafka-topics \
    --create \
    --topic create-product-v1 \
    --partitions 8 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper localhost:2181

docker-compose exec kafka kafka-topics \
    --create \
    --topic delete-product-v1 \
    --partitions 8 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper localhost:2181

docker-compose exec kafka kafka-topics \
    --create \
    --topic update-product-v1 \
    --partitions 8 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper localhost:2181

docker-compose exec kafka kafka-topics \
    --zookeeper zookeeper:2181 \
    --describe \
    --topic create-product-v1

docker-compose exec kafka kafka-consumer-groups \
    --bootstrap-server localhost:9092 \
    --describe \
    --group product-manager-v1

