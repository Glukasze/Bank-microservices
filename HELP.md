# Getting Started

### Useful info
Collections of useful tips scrips and other for running bank microservices.

* Run rabbitMQ container:
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
* Create MySQL database containers:
```bash
docker run -p 3308:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
docker run -p 3308:3307 --name cardsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsdb -d mysql
docker run -p 3308:3308 --name loansdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansdb -d mysql
```
* Create docker images for microservices
```bash
cd configserver && mvn compile jib::dockerBuild && cd ../accounts && mvn compile jib:dockerBuild && cd ../cards && mvn compile jib:dockerBuild && cd ../loans && mvn compile jib:dockerBuild && cd ..
```
* Run docker compose
```bash
cd docker-compose/default && docker compose up -d && cd ../..
```