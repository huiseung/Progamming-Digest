# Redis 
- 데이터베이스에 접근해 데이터를 읽는 행위는 시간 소모가 크다.
- 따라서 빈번하게 읽는 데이터는 캐시 서버에 저장해 두고 읽어 시간 효율성을 높일 수 있다.
- redis는 캐시 서버로 많이 사용하는 inmemory key-value 방식에 NoSQL DataBase다.
  - 단순히 빈번하게 읽는 데이터를 저장해두는 기능 외에도 Ranking 자료구조를 제공해 관련 서비스 구현에 쓸 수 있다
- 캐시시 고려할 사항
  - 무엇을 캐시할까
  - 유효시간
  - 정보갱신

### 설치
1) 이미지 다운로드: docker pull redis:5.0.3
2) 실행: docker run --name myredis -d -p 6379:6379 redis
3) CLI접속: docker run -it --link myredis:redis --rm redis redis-cli -h redis -p 6379

## 명령어
- redis는 모든 데이터를 key : data 형태로 관리하며, 이때 data는 다양한 자료형일 수 있다. 자료형 구분은 명령어로 한다
### primitive(string or int)
- set [primitiveKey] [value] : key에 value(string or int)를 저장한다, 이미 key에 value가 있을 경우 덮어쓰기가 된다
- get [primitiveKey] : value를 반환한다
- incr [primitiveKey] : value가 int일때 값을 1 증가시킨다
- decr [primitiveKey] : value가 int일때 값을 1 감소시킨다
- incrby [primitiveKey] [term] : value가 int일때 term만큼 증가 시킨다 
- decrby [primitiveKey] [term] : value가 int일때 term만큼 감소 시킨다

### List
- rpush [listKey] [element...]
- lpush [listKey] [element...]
- lrange [listKey] [stant] [end]
 
### set
- sadd [setKey] [element...]
- smebers [setKey]

### sorted set
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
- 

### Dict
- hset [dictKey] [field] [value] : field에 value 저장, 이미 값이 있을 경우 덮어쓰여진다.
- hget [dictKey] [field] : field에 대응하는 value를 반환
- hgetall [dictKey] : dict에 저장된 모든 (field, value)를 반환
