#!/bin/sh

# Build the app
./gradlew clean build

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    # Build the docker image and tag as latest
    docker build --tag stackline-product-read-v1:latest .

    # Run the platform image
    docker run -it --rm \
        --memory 128m \
        --publish 8081:8080 \
        --env APP_ENV=test \
        --name stackline-product-read-v1 \
        stackline-product-read-v1:latest
else
    echo "Build failed"
fi
