#!/bin/sh

# Spin up the stack
docker-compose up -d

# Create the product topics (create, delete, update)
docker-compose exec kafka kafka-topics \
    --create \
    --topic create-product-v1 \
    --partitions 16 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper zookeeper:2181

docker-compose exec kafka kafka-topics \
    --create \
    --topic delete-product-v1 \
    --partitions 16 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper zookeeper:2181

docker-compose exec kafka kafka-topics \
    --create \
    --topic update-product-v1 \
    --partitions 16 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper zookeeper:2181

exit 1

# List topics
docker-compose exec kafka kafka-topics \
    --zookeeper zookeeper:2181 \
    --list

# Describe create product topic
docker-compose exec kafka kafka-topics \
    --zookeeper zookeeper:2181 \
    --describe \
    --topic create-product-v1

# Describe the product manager consumer group
docker-compose exec kafka kafka-consumer-groups \
    --bootstrap-server localhost:9092 \
    --describe \
    --group product-manager-v1

