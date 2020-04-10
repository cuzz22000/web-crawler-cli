#!/bin/bash

echo "$DOCKER_PWD" | docker login --username "$DOCKER_USR" --password-stdin
docker build -t $IMAGE_NAME:latest .
docker images
docker tag $IMAGE_NAME:latest $IMAGE_NAME:$TRAVIS_TAG
docker push $IMAGE_NAME:latest
docker push $IMAGE_NAME:$TRAVIS_TAG