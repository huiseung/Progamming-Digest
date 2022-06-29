# 목차


# Message Queue
## 필요성
- message broker(queue system)는 msa에서 
- 오래 걸리는 작업은 비동기 처리 방식을 사용해 클라이언트가 해당 작업을 기다리지 않게 할 수 있다
  

## Redis, Kafka, RabbitMQ 비교
- 고려 사항
  - scale: 초당 전송 메세지 수
  - persistency: 메세지 영속성
  - capability: cbroker(하나에 queue)가 생산자:소비자를 1:1, 1:N으로 연결 가능한가 

| | RabbitMQ | Kafka | Redis |
|---|---|---|---|
|scale| 50,000 msg/sec| 10,000,000 msg/sec | 1,000,000 msg/sec |
|persistency| 가능 | 가능 | 불가 |
|capability|1:1, 1:N 둘 다 가능| 1:N 가능 | 1:1, 1:N 둘 다 가능|

- 대량에 메세지: kafka
- 짧은 생명으로 충분한 메세지: Redis

- 출처: Redis, Kafka, or RabbitMQ: Which Micro Services Message Broker To Choose ?
  - https://otonomo.io/redis-kafka-or-rabbitmq-which-microservices-message-broker-to-choose/

