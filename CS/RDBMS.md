# 목차
- [목차](#목차)
- [테이블 설계](#테이블-설계)
  - [ER diagram](#er-diagram)
    - [B+tree](#btree)
  - [정규화](#정규화)
- [SQL](#sql)
- [Transaction](#transaction)
  - [Concurrency Control](#concurrency-control)
- [효율성 높이기](#효율성-높이기)
  - [복제, 레플리카 replica](#복제-레플리카-replica)
  - [파티셔닝 partitioning](#파티셔닝-partitioning)
  - [샤딩 sharding](#샤딩-sharding)
- [NoSQL Intro](#nosql-intro)
  - [Elastic Search](#elastic-search)
  - [Redis](#redis)
  - [Mongo DB](#mongo-db)

---------------------------------------------

# 테이블 설계
## ER diagram
- 엔티티 entity
  - 테이블상에 레코드로 저장된다 

- 속성 attribute

- 키 key
  - 기본키 Primary Key
    - 테이블에서 레코드를 구분하는 속성
    - 복수에 속성을 합쳐 기본키를 이룰 수 있다 
  - 후보키 candidate key
    - 기본키가 될 수 있는 가능성있는 속성
  - 왜래키 Foregin Key
    - 다른 테이블과 관계를 맺기 위해 사용하는 키 

- 관계 relationship
  - 1:1
  - 1:N
  - N:N

</br>
<details>
<summary>
DBMS에서 index란 무엇인가
</summary>
</br>
<p>
select 속도를 빠르게 하기 위해 사용하는 자료구조(ex B+tree)</br>
두개 이상 컬럼을 묶어 index를 만들 수 있다
</p>
<p>단점
<ul>
<li>index를 만든 컬럼에 데이터 삽입, 삭제, 수정 연산에 시간이 걸기전 보다 늘어난다</li>
</ul>
</p>
</details>

### B+tree
- 하나에 노드가 m 개에 값을 갖을 수 있는 트리  

- 빠른 검색을 위한 필수 조건, 찾고자 하는 값이 있는지 빠르게 판단할 수 있어야 한다.
  - 선형 탐색은 검색 조건에 걸리지 않을때 O(N)에 시간을 소모한다



</br>
<details>
<summary>
DBMDS에서 View란 무엇인가
</summary>
<p>하나이상에 테이블을 합쳐 만든 가상 테이블</p>
<p>장점</p>
<ul>
<li>자주 사용하는 질의를 뷰로 만들어 놓을 수 있다</li>
<li>선별한 컬럼만 조회가능하게 해 보안성을 높인다</li>
</ul>
<p>단점</p>
<ul>
<li>독립적인 인덱스를 가질 수 없다</li>
</ul>
</details>
</br>



## 정규화

- 함수 종속성: 어떤 컬럼 집합 A에 값을 알면 다른 컬럼 집합 B에 값이 유일하게 정해질때, B는 A에 종속한다, A는 B를 결정한다. A->B 로 표기
- 완전 함수 종속: 어던 컬럼집합 A와 다른 컬럼 집합 B가 A->B 종속 관계일때, A에 부분집합은 B와 종속관계를 이루지 않는 관계

<ul>
<li> 
<p>제1 정규화</p>
<p>모든 속성은 원자값을 갖는다. 복수에 값을 하나에 컬럼에 넣지 않는다</p>
</li>
<li> 
<p>제2 정규화</p>
<p>제1 정규화를 만족하며, 기본키와 기본키가 아닌 컬럼들이 완전 함수 종속관계를 이루는 상태</p>
<p>만드는 법: 기존에 부분 함수 종속을 이루는 것끼리 테이블을 이루게 쪼갠다</p>
</li>
<li>
<p>제3 정규화</p>
<p>제2 정규화를 만족하며, 기본키가 아닌 컬럼끼리 종속 관계를 이루지 않는 상태</p>
<p>만드는 법: 기본키가 아니면서 종속관계를 이루는 컬럼들을 테이블에서 쪼갠다</p>
</li>
<li>
<p>BCNF 정규화</p>
<p>후보키만 결정자가 되는 상태</p>
</li>
</ul>

---------------------------------------------

# SQL
- ddl: 테이블 설정
- dml: CRUD
- dcl: 사용 권한 관리

----------------------------------------------

# Transaction
- 데이터베이스 상태 변경 작업 단위
  - 하나 이상에 쿼리가 합쳐져 하나에 작업을 이룬다 
  - 하나에 작업에서 여러 쿼리가 절차적으로 진행된다
- commit: 데이터베이스에게 트랜잭션이 정상 처리되었음을 알리는 명령어
  - 메모리에서 처리 작업한 내용을 디스크에 실제 쓰는 행위
- rollback: 데이터베이스에게 트랜잭션이 실패해 처리전 상태로 돌리라는 명령어
  - 메모리에서 처리 작업한 내용을 디스크에 반영하지 않고 날리는 행위
- TPS: 초당 트랜잭션 실행 수, 데이터베이스에 성능 측정 지표

</br>
<details>
<summary>
Transaction에 ACID란 무엇인가 
</summary>
<ul>
<li>Atomic 원자성: 데이터베이스는 트랜잭션 처리 전, 후 두 상태만 가능하다.</br> 하나에 연산이 실패하면 트랜잭션이 실패한다</li>
<li>Consistency 일관성: 트랜잭션 처리를 해도 제약사항은 유지된다</br>제약사항을 지키는 트랜잭션만 수행가능</li>
<li>Isolation 독립성: 여러 트랜잭션이 동시 작업중일때 서로 영향을 주지 않는다</li>
<li>Durability 지속성: 트랜잭션 처리가 끝나 commit된 결과는 데이터베이스에 영구 반영된다</li>
</ul>
</details>
</br>

## Concurrency Control
- 동시다발로 발생하는 트랜잭션들을 안전하게 처리하기 위해선 락을 사용해야한다
- lock
  - 공유락 shared lock: 트랜잭션이 읽기중일때 사용하는 락, 다른 트랜잭션은 해당 데이터를 읽기만 가능하다
  - 배타락 exclusive lock: 트랜잭션이 쓰기중일때 사용하는 락, 다른 트랜잭션은 해당 데이터를 읽거나 쓸수 없다
  - 락범위: row, column, table, database
  - 데드락 dead lock: 두 트랜잭션이 각각 락을 걸고 있고, 서로 락을 걸고있는 데이터에 접근해 양쪽다 작업을 수행하지 못하고 멈추는 상태

</br>
<details>
<summary>
Transaction에 동시성 제어 실패시 발생할 수 있는 문제
</summary>
</br>
<ul>
    <li>
    <p>유령 읽기 Phantom Read</p>
    <p>T1이 테이블을 2번 조회하는 연산을 하고, T2는 테이블에 레코드 삽입하는 연산을 할때, </br>"T1 첫번째 조회 -> T2 삽입 -> T1 두번째 조회"로 처리되면 첫번째와 두번째 조회 결과가 달라버리는 문제 발생</p>
    </li>
    <li>
    <p>읽기 반복불가 Unrepeatable Read</p>
    <p>T1이 테이블을 2번 조회하는 연산을 하고, T2는 테이블에 레코드 변경하는 연산을 할때, </br>"T1 첫번째 조회 -> T2 변경 -> T1 두번째 조회"로 처리되면 첫번째와 두번째 조회 결과가 달라버리는 문제 발생</p>
    </li>
    <li>
    <p>오손 읽기 Dirty Read</p>
    <p>T1이 테이블 변경 연산, T2가 테이블 조회 연산을 할때, </br> "T1 commit 전 T2 조회"로 처리되면 변경되지 않는 데이터를 읽는 문제 발생</p>
    </li>
</ul>
</details>
</br>

</br>
<details>
<summary>
Transaction에 Isolcation level이란 무엇인가
</summary>
</br>
<p>빠른 트랜잭션 처리를 위해 트랜잭션간 작업도중 끼어들기를 허용하는 수준</br>
높을 수록 속도는 느리지만 확실한 동시성 제어를 보장한다</p>
<ul>
<li>
<p>READ_UNCOMMITED: level 0</p>
<p>T1이 commit하기 전 처리중인 데이터를 T2가 조회하는걸 허용</p>
<p>막을 수 있는 문제 없음</p></li>
<li>
<p>READ_COMMITED: level 1</p>
<p>T1이 commit한 데이터만 T2가 조회가능</p>
<p>더티리드 막음</p></li>
<li>
<p>REPEATABLE_READ: level 2</p>
<p>T1이 조회중인 데이터는 commit전까지 T2가 수정, 삭제 불가</p>
<p>더티리드, 읽기 반복불가 막음</p></li>
<li>
<p>SERIALIZABLE: level 3</p></p></li>
<p>T1 commit후 T2 실행</p>
<p>더티리드, 읽기 반복불가, 팬텀리드 막음</P>
</ul>
</details>
</br>


----------------------------------------

# 효율성 높이기
## 복제, 레플리카 replica


## 파티셔닝 partitioning


## 샤딩 sharding


--------------------------------------

# NoSQL Intro
## Elastic Search

## Redis

## Mongo DB