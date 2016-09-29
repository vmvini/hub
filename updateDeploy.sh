echo "compilando middleware"
mvn -f middleware/middleware/pom.xml clean install
echo "reiniciando somente containers alterados"
docker-compose up -d --build