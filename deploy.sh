#!/bin/bash
echo "parando containers ativos (se houver)"
docker stop $(docker ps -a -q)
echo "compilando middleware"
mvn -f middleware/middleware/pom.xml clean install
echo "docker-compose build"
docker-compose build
echo "docker-compose up"
docker-compose up