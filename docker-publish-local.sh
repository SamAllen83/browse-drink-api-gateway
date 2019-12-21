#!/usr/bin/env bash
mvn clean install -Dspring.cloud.contract.verifier.skip=true -DskipTests
docker build -t localhost:32000/browse-drink-api-gateway .
docker push localhost:32000/browse-drink-api-gateway