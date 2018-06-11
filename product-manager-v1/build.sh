#!/bin/sh

# Build the app
./gradlew clean build

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    # Build the docker image and tag as latest
    docker build --tag stackline-product-manager-v1:latest .

    # Run the platform image
    #--network stackline_default \
    docker run -it --rm \
        --memory 128m \
        --publish 8082:8082 \
        --env APP_ENV=test \
        --env KAFKA_HOSTS="localhost:9092" \
        --env ES_HOST=localhost \
        --name stackline-product-manager-v1 \
        --network host \
        stackline-product-manager-v1:latest
else
    echo "Build failed"
fi
