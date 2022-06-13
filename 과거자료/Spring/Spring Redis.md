# 목차
- [목차](#목차)
- [Redis](#redis)
  - [Redis 사용 목적](#redis-사용-목적)
  - [Cache 사용 목적](#cache-사용-목적)
- [Redis setting](#redis-setting)
    - [설치](#설치)
    - [연결 설정](#연결-설정)
    - [cli 실행](#cli-실행)
- [Redis 기본 명령어](#redis-기본-명령어)
  - [Value](#value)
  - [sets](#sets)
  - [sorted sets](#sorted-sets)
  - [lists](#lists)
  - [dict](#dict)
- [RedisRepository](#redisrepository)
- [spring cache](#spring-cache)
  - [어노테이션](#어노테이션)

# Redis
- key-value nosql database
  - 저장 데이터 구조로 string, int, set, sorted set, list, dict 지원
## Redis 사용 목적
- cache
- shared memory 
- message queu

## Cache 사용 목적
- 적용 대상: 빈번한 조회가 발생하며, 변경이 자주 일어나지 않는 데이터
  - 공지사항
  - 베스트 셀러
- 목표: db 조회 횟수를 줄여 애플리케이션에 성능 높이기


# Redis setting
### 설치
- 이미지
```
Docker image pull redis
```
- 의존성 라이브러리
```
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```

### 연결 설정
- docker-compose.yml
```yml
version: "3"
services:
  redis:
    build: redis
    container_name: redis
    ports:
      - 6379:6379
    networks:
      - default

```

- application.yml
```yml
spring:
  redis:
    host:
    port: 6739
```

### cli 실행
컨테이너 cli를 켠 후 명령어로 redis server 접속
```
//서버 접속
>redis-cli
```

# Redis 기본 명령어
## Value
```
set key value //key:value 데이터 생성
mset key1 val1 ke2 val2 //여러개에 key:value 데이터 생성 
setex key 10 value  //10초 후 사라지는 key:value 데이터 생성
```

```
keys * //생성된 key들 조회
get key //key에 대응하는 value 조회, 없으면 nil 리턴
mget key1 key2 //여러 key에 대응하는 value 조회
ttl key //key:data 데이터에 수명(초) 반환, 무한일 경우 -1, 없는 데이터면 -2
```

```
rename key newkey //key 이름을 newkey로 변경
incr counter //counter란 key에 대응하는 value에 내용이 정수일때, 그 값을 1 증가
incrby counter 50 ////counter란 key에 대응하는 value가 정수일때, 그 값을 50 증가
```

```
del key //key:value 데이터 삭제, 성공시 1, 실패시 0
flushall //모든 key:value 데이터 삭제
```

```java
ValueOperations<String, String> valueOperations = redisTemplate.opForValue();
valueOperations.set("key", "value");
String value = valueOperations.get("key");
```


```java
ValueOperations<String, String> valueOperations = redisTemplate.opForValue();
valueOperations.set("key", "1");
String value = valueOperations.increment("key"); //incr key
```


## sets
```java
SetOperations<String, String> setOperations = redisTemplate.opForSet();
setOperations.add("key", "e");
Set<String> eSet = setOperations.members("key");

```

## sorted sets
```java
ZSetOperations<String, String> zsetOperations = redisTemplate.opForZSet();
```

- Create
  - zadd [sortedSetKey] [score] [element] : (score, element) 형태로 저장
- Read
  - zscore [sortedSetKey] [element] : element의 score 조회
  - zrange [sortedSetKey] [start] [end] : start ~ end 등수 score오름차순 조회
  - zrevrange [sortedSetKey] [start] [end] : start ~ end 등수 score내림차순 조회
  - zrank [sortedSetKey] [element] : element의 score오름차순 순위를 조회
  - zrevrank [sortedSetKey] [element] : element의 score내림차순 순위를 조회
  - zrangebyscore [sortedSetKey] [min] [max] : min ~ max 범위에 있는 score오름차순 조회 
  - zrevrangescore [sortedSetKey] [max] [min] : max ~ min 범위에 있는 score내림차순 조회
- Update
  - zincrby [sortedSetKey] [term] [element]: element의 score를 해당 term 만큼 증가(term이 양수)/감소(term이 음수)시킨다
- Delete
  - zremrangebyrank [sortedSetKey] [rank] : 해당 rank의 element 제거
  - zremrangebyscore [sortedSetKey] [min] [max] : min ~ max 범위에 있는 score를 갖는 element 제거


## lists

```java
ListOperations<String, String> listOperations = redisTemplate.opForList();
listOperations.rightPush("key", "e");
listOperations.rightPushAll("key", "e1", "e2");
String e = listOperations.index("key", 1);
List<String> eList = listOperations.range("key", 0, 2);
Long size = listOperations.size("key");

```

- rpush [listKey] [element...]
- lpush [listKey] [element...]
- lrange [listKey] [stant] [end]

## dict

```java
HashOperations<String, Object, Object> hashOperations = redisTemplate.opForHash();
hashOperations.put("key1", "ckey1", "val1");
hashOperations.put("key1", "ckey2", "val1");
Map<Object, Object> map = redisTemplate.entries("key1");
```

- hset [dictKey] [field] [value] : field에 value 저장, 이미 값이 있을 경우 덮어쓰여진다.
- hget [dictKey] [field] : field에 대응하는 value를 반환
- hgetall [dictKey] : dict에 저장된 모든 (field, value)를 반환




# RedisRepository
```java
public class Some{

}
```
```java
public interface SomeRedisRepository extends CrudRepository<Some, String>{}
```

- @EnableRedisRepositories


# spring cache
## 어노테이션
- import org.springframework.cache.annotation.Cacheable;
- @Cacheable(key="#", value=)
  - 캐시 서버 데이터 조회, 없으면 등록
  - json array는 등록(deserialze 필요) 하는 방법을 못 찾았다. 대신, 객체로 하번 감싸 json array를 filed중 하나로 두면 deserialize 가능하다
- @CachePut
  - 캐시 서버 데이터 업데이트
- @CacheEvict
  - 캐시 서버 데이터 삭제


