# Elastic Search

# Logstash

# Kibana


# 설치및 실행

```sh
<<END
elastic search
END

docker pull docker.elastic.co/elasticsearch/elasticsearch:7.9.1

docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name elasticsearch7 docker.elastic.co/elasticsearch/elasticsearch:7.9.1

<<END
kibana
END

docker pull odcker.elastic.co/kibana/kibana:7.9.1

docker run -d --link elasticsearch7:elasticsearch -p 5601:5601 --name kibana7 docker.elastic.co/kibana/kibana:7.9.1

```


```yml
"""
docker-compose
"""

version: '3'
services:
    elasticsearch:
        image:
        continaer_name:
        ports:
            - "9200:9200"
            - "9300:9300"
        environment:
            - node.name = "es01"
            - cluster.name = "es-docker-cluster"
            - discovery.type=single-node
        networks:
            elastic

networks:
    elastic:
        diver: bridge

```


------------
# Spring Data Elastic Search 공식 문서 번역

- 문서 저장, 검색, 정렬및 집계를 위한 고수준 추상화 템플릿 


# version 정보

##  Spring Data Elasticsearch 4.3
- elastic search 7.15.2 이상
- index mapping에 runtime_fields 정의 가능
- range object에 사용 가능한 range filed types를 기본 지원
- nullable or empty 속성을 위한 repository 검색 추가
- single fields를 위한 converters 커스텀 가능
- Sort.Order 커스텀 가능

## 호환 정보

|Spring Data Elastic Search|Elastic Search|Spring Boot|Spring|
|---|---|---|---|
|4.3|7.15.2|2.5|5.3|
|4.2|7.12.0|2.5|5.3|

# Spring Data Repository와 함께 사용하기
- Spring Data Repository는 data 접근 관련 코드양을 줄일 수 있게 도와준다
- CrudRepository Interface는 entity class에 CRUD 기능을 제공
  - ex) save, findById, findAll, delete, count, existsById  
- JpaRepository, MongoRepository같은 CrudRepsitory에 확장형태에 Interface도 있다
- 



------
# reference
- https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#preface
- https://docs.spring.io/spring-data/elasticsearch/docs/current/api/