### Open API / Swagger documentation
the following is the link to the api documentation, generated with open API

http://localhost:8080/hotel/swagger-ui/index.html#/

###  KAFKA

###  Kafka Commands

```console
kafka-topics --create --topic availability --bootstrap-server localhost:9092

kafka-topics --create --topic hotel --bootstrap-server localhost:9092

kafka-console-producer  --topic availability --bootstrap-server localhost:9092

kafka-console-consumer --topic availability --from-beginning --bootstrap-server localhost:9092

kafka-console-producer  --topic hotel --bootstrap-server localhost:9092

kafka-console-consumer --topic hotel --from-beginning --bootstrap-server localhost:9092

docker-compose up =>>  /src/main/resources/kafka/docker-compose.yml
```


###  MONGO

###  Mongo Commands
```console

docker-compose up =>>  /src/main/resources/docker-compose.yml
```


 
