#!/bin/sh

# Build the app
./gradlew clean build

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    # Build the docker image and tag as latest
    docker build --tag stackline-product-write-v1:latest .

    # Run the platform image
    docker run -it --rm \
        --memory 64m \
        --publish 8080:8080 \
        --env APP_ENV=test \
        --name stackline-product-write-v1 \
        --network host \
        stackline-product-write-v1:latest
else
    echo "Build failed"
fi
