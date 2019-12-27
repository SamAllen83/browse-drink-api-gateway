#!/usr/bin/env bash
mvn clean install -Dspring.cloud.contract.verifier.skip=true -DskipTests
docker build -t browse-drink-api-gateway .