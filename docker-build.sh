mvn clean install
docker build --no-cache -t docker-spring:1.2 .
docker-compose down
docker-compose up -d