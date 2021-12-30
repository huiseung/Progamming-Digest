# 목차
- [목차](#목차)
- [ER Diagram](#er-diagram)
  - [Normalize](#normalize)
  - [View](#view)
- [SQL](#sql)
- [Transection](#transection)
  - [Concurrency Control](#concurrency-control)
  - [Isolation Level](#isolation-level)
    - [HowTO](#howto)
- [Index](#index)
  - [B+tree](#btree)
    - [추가, 삭제 비용 절감](#추가-삭제-비용-절감)
- [대용량 트래픽 처리](#대용량-트래픽-처리)
- [Replica](#replica)
  - [Database가 Crash 되는 상황](#database가-crash-되는-상황)
  - [HowTo](#howto-1)
- [Sharding](#sharding)
  - [HowTo](#howto-2)
- [NoSQL Intro](#nosql-intro)

ㅏ
# ER Diagram
## Normalize
## View

# SQL

# Transection
## Concurrency Control

## Isolation Level
### HowTO


# Index
- 검색 성능을 높이기 위해 사용, 검색 시간 단축
- 해당하는 값을 빠르게 찾기위한 자료구조 활용
- 주로 B+tree 자료구조를 이용한다

## B+tree
- 루트 노드, 브랜치 노드: 리프노드까지 찾아가는데 필요한 키를 담고 있는 노드
- 리프 노드: 실제 사용하는 값과 대응하는 키를 갖고 있는 노드, 리프노드들도 링크드 리스트로 구현되어 인접한 리프 노드를 탐색 할 수 있다.

### 추가, 삭제 비용 절감
- B+tree에 단점은 추가, 삭제 overhead가 크다는 점이다.
- MySQL은 추가/삭제 정보들을 메모리에 일시 기록해 두고 추후 한 번에 갱신하는 방법을 취한다.


# 대용량 트래픽 처리
- cache
- clustering
- replica
- sharding

# Replica
- CRUD 성능을 높이기 위해 사용, 트래픽 분산
- Primary(쓰기 전용) DB, Secondary(검색 전용) DB 를 따로 두는 설계 방식

## Database가 Crash 되는 상황

## HowTo

# Sharding
- CRUD 성능을 높이기 위해 사용, 트래픽 분산

- 종류
  - moduler sharding
  - range sharding

## HowTo

# NoSQL Intro