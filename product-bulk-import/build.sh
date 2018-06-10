#!/bin/sh

./gradlew clean build

STATUS=$?

if [ ${STATUS} -eq 0 ]; then
    # Build the docker image and tag as latest
    docker build --tag stackline-product-bulk-import-v1:latest .

    # Run the platform image
    docker run -it --rm \
        --memory 128m \
        --env APP_ENV=test \
        --name stackline-product-bulk-import-v1 \
        stackline-product-bulk-import-v1:latest
else
    echo "Build failed"
fi
