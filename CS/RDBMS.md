# 목차
- [목차](#목차)
- [RDBMS](#rdbms)
- [SQL](#sql)
  - [논리적 실행 과정](#논리적-실행-과정)
- [MySQL](#mysql)
  - [docker container 생성](#docker-container-생성)
  - [설정 파일](#설정-파일)
    - [데이터 베이스](#데이터-베이스)
    - [테이블 생성](#테이블-생성)
    - [테이블 조회](#테이블-조회)
    - [테이블 제거](#테이블-제거)
    - [테이블 수정](#테이블-수정)
    - [튜플 삽입](#튜플-삽입)
    - [튜플 수정](#튜플-수정)
- [테이블 설계](#테이블-설계)
    - [ER diagram](#er-diagram)
    - [index](#index)
    - [view](#view)
    - [테이블 정규화](#테이블-정규화)
- [Transaction](#transaction)
    - [Concurrency Control](#concurrency-control)
    - [Recovery](#recovery)
- [복제 replication](#복제-replication)
- [파티셔닝](#파티셔닝)
- [샤딩](#샤딩)
  - [# cache](#-cache)
  - [NonRDB & NoSQL Intro](#nonrdb--nosql-intro)


# RDBMS
- 데이터 베이스 기능
  - CRUD, 물리적 위치가 아닌 값을 이용해 참조 가능
  - 실시간 서비스
  - 동시 접속
  - 영속성
  - 무결성

- RDMBS
  - entity를 table로 entity간에 relation을 Foreign key를 이용해 표현하는 데이터베이스
  - 중복 tuple를 허락하지 않는다
  - attiribute
  - tuple
  - primary key(PK)
    - super key: tuple을 식별할 수 있는 attribute set
    - candidate key: super key중 하나에 attribute만 빠져도 tuple을 식별할 수 없는 attribute set
    - primary key: candidate key중 설계자가 tuple을 구분하기 위해 지정한 attribute set
    - attribute set이라 표현한 이유는 복수에 attribute가 모여 하나에 key를 이룰 수 있기 때문
  - foreign key(FK)
    - 다른 table에 primary key를 참조하는 attribute set

- 무결성
  - 도메인 무결성
    - attribute에 type, nullable, default를 지키는 삽입/수정만 가능
  - 개체 무결성
    - PK가 같으면 삽입/수정 금지, PK를 null로 삽입/수정 금지
  - 참조 무결성
    - 참조하고 있는 table에 없는 PK를 FK로 삼는 삽입/수정 금지
    - FK로 참조되고 있는 PK를 갖고있는 table에서 tuple 삭제는 해당 tuple에 PK와 같은 FK를 갖는 참조 table에 tuple도 같이 삭제


# SQL
## 논리적 실행 과정
1. 구분 분석
2. 표준화
3. 최적화
4. 컴파일
5. 실행
   1. FROM 테이블 조회
   2. WHERE 조건 탐색
   3. GROUP BY 가공
   4. HAVING 가공후 조건 처리
   5. SELECT 뽑아냄
   6. ORDER BY 정렬

# MySQL
- mysql 접속
  - mysql -u 사용자명 -h 엔드포인트 -P 포트번호

- 아키택처
  - MySQL Connector
  - MySQL Server Process
  - Storage Engine
  - OS
- 쿼리 실행 과정

## docker container 생성
```yml

```

## 설정 파일



### 데이터 베이스
- 데이터 베이스
  - 조회 show databases;
  - 생성 create database 데이터베이스이름;
  - 사용 use 데이터베이스이름;
  - 삭제 
  - 존재하는 테이블 조회 show tabales;

### 테이블 생성
- 생성 create 테이블이름(스키마);
  - 컬럼 설정: 이름 타입 제약
  - 키 설정  
  - 인덱스 설정
- 컬럼 설정
  - type
    - VARCHAR
    - INT
    - DATETIME
      - 같은 datetime 타입끼리 크기 비교 가능
  - 제약
    - not null
    - default 
    - unique
  - 키
    - primaray
    - foreign
  - index

### 테이블 조회
- SELECT 테이블별칭.컬럼 FROM 테이블 AS 테이블별칭 
- JOIN
  - INNER JOIN 조인할테이블 AS 조인할테이블별칭 ON 테이블별칭.컬럼 = 조인할테이블별칭.컬럼
  - LEFT OUTER JOIN 조인할테이블 AS 조인할테이블별칭 ON 테이블별칭.컬럼 = 조인할테이블별칭.컬럼
-서브쿼리
  - SELECT * FROM (SELECT * FROM 테이블) AS 서브쿼리별칭
- 조건문
  - WHERE 테이블별칭.컬럼 IS NULL
  - WHERE 테이블별칭.컬럼 IS NOT NULL
  - WHERE 테이블별칭.컬럼 > 값
  - WHERE 테이블별칭.컬럼 IN (값1, 값2, 값3)    매칭되길 바라는 값이 여러개일경우 이용
  - WHERE 조건1 AND 조건2
  - WHERE 조건1 OR 조건2
  - WHERE 테이블별칭.컬럼 LIKE '%값%'
  - WHERE EXISTS(서브쿼리)
- 순서
  - 기본 ASC
  - ORDER BY 테이블별칭.컬럼 ASC
  - ORDER BY 테이블별칭.컬럼 DESC
  - ORDER BY 테이블별칭.컬럼1 ASC, 테이블별칭.컬럼2 ASC
- TOP K
  - LIMIT 숫자

- 내장함수
  - AVG, COUNT, MAX, MIN
  - ABS, ROUND

### 테이블 제거
- 테이블 자체 제거
  - drop table 테이블이름
  - 자동 commit
- 스키마는 유지한체 데이터만 전체 제거
  - truncate table 테이블이름
  - 자동 commit
- 특정 행 제거
  - delete from 테이블이름 where 조건
  - 제거된 행이 사용하던 storage 공간은 계속 사용중이다. commit 명령어 수행전이면 rollback을 통해 되돌릴 수 있다

### 테이블 수정
- ALTER TABLE 테이블이름 ADD 컬럼추가사항
- ALTER TABLE 테이블이름 MODIFYE 컬럼변경사항
- ALTER TABLE 테이블이름 DROP COLUMNS 제거컬럼이름

### 튜플 삽입
- INSERT INTO 테이블이름 (컬럼들) VALUES (값들);
- bulk insert 대량에 데이터를 묶어 삽입 쿼리 날리기
  - INSERT INTO 테이블이름 (컬럼들) VALUES (값들1), (값들2), .... ;
  - LOAD DATA INFILE 파일이름 INTO 데이터베이스이름.테이블이름;

### 튜플 수정
- UPDATE 테이블이름 SET 컬럼=수정값 WHERE 조건문;

-----

# 테이블 설계
### ER diagram


### index
- 장점
  - 빠른 검색 가능
- 단점
  - 수정 삭제가 자주 발생하는 컬럼에 대해서는 인덱스를 적용시 오버헤드가 크다

- B+tree
  - B tree: 3개 이상에 자식을 갖는 트리
  - 리프 노드에 값을 리프 노드가 아닌 노드에는 원하는 값이 위치한 리프노드 키까지 찾아가는데 필요한 키가 저장되어 있다

- 결합 인덱스


### view

- 하나이상에 테이블을 합쳐 만든 가상 테이블
- 장점
  - 자주 사용하는 질의를 뷰로 만들어 놓을 수 있다
  - 선별한 컬럼만 조회가능하게 해 보안성을 높인다
- 단점
  - 독립적인 인덱스를 가질 수 없다

### 테이블 정규화

- 함수 종속성: 어떤 컬럼 집합 A에 값을 알면 다른 컬럼 집합 B에 값이 유일하게 정해질때, B는 A에 종속한다, A는 B를 결정한다. A->B 로 표기
- 완전 함수 종속: 어던 컬럼집합 A와 다른 컬럼 집합 B가 A->B 종속 관계일때, A에 부분집합은 B와 종속관계를 이루지 않는 관계
- 이상현상
  - 삽입을 위해서 불필요하게 null을 넣는 컬럼들이 발생
  - 어떤 컬럼에 조건을 주어 다른 컬럼을 수정을 할때, 조건을 준 컬럼이 바꾸어야할 컬럼을 다 포함시키지 못하는 문제
  - 어떤 컬럼에 값에 해당하는 튜플을 삭제할때 남아있어야할 정보들까지 같이 삭제되는 문제  
- 테이블 정규화: 이상현상이 발생하는 테이블을 이상현상이 없는 테이블들로 쪼개는 행위
- 제1 정규화
  - 모든 속성은 원자값을 갖는다. 복수에 값을 하나에 컬럼에 넣지 않는다
- 제2 정규화
  - 제1 정규화를 만족하며, 기본키와 기본키가 아닌 컬럼들이 완전 함수 종속관계를 이루는 상태
  - 기존에 부분 함수 종속을 이루는 것끼리 테이블을 이루게 쪼갠다
- 제3 정규화
  - 제2 정규화를 만족하며, 기본키가 아닌 컬럼끼리 종속 관계를 이루지 않는 상태
  - 기본키가 아니면서 종속관계를 이루는 컬럼들을 테이블에서 쪼갠다
- BCNF 정규화
  - 후보키만 결정자가 되는 상태


------

# Transaction
- DBMS에서 데이터를 다루는 작업 단위
- 트랜잭션에 특징 ACID
  - 원자성 Atomicity 
    - 데이터베이스는 트랜잭션 처리 전 후 상태로만 존재
  - 일관성 Consistency 
    - 트랜잭션 수행 후에도 제약사항을 지키고 있다
  - 격리성 Isolation 
    - 수행중인 트랜잭션끼리 서로 영향을 주지 않는다
    - 격리 수준: 어느 정도끼리 영향을 막을 것인가
  - 지속성 Durability 
    - 트랜잭션 처리가 끝나 commit된 결과는 장애가 발생해도 영구 반영되어야 한다


### Concurrency Control
- 동시성 제어를 하지 않으면 발생 할 수 있는 문제
  - 오손 읽기 Dirty Read
    - 수정 작업이 수행 되기전, 해당 데이터를 다른 트랜잭션이 읽음
  - 반복불가 읽기 Unrepeatable Read
    - 트랜잭션1이 수정후 읽으려는데, 트랜잭션2가 트랜잭션1이 수정만하고 읽기전에 해당 데이터에 수정 작업을 진행
  - 유령데이터 읽기 Phantom Read
    - 트랜잭션1이 연속해서 두번 읽을때, 트랜잭션이2가 트랜잭션1이 처음 읽고 두번째 읽기전 데이터를 수정
  - 갱신 손실 Lost Update
    - 수정 작업이 반영 되기 전, 해당 데이터를 다른 트랜잭션이 수정해 앞선 작업을 덮어씌움
  - 모순성 Inconsistency
    - 수정 작업을 시작하기 전, 해당 데이터를 다른 트랜잭션이 수정해 앞선 작업이 시작과 다른 값을 기준으로 수정을 해야하는 상황
  - Cascading Rollback
    - 수정 작업을 실패해 롤백하기 전, 해당 데이터를 다른 트랜잭션이 수정해 커밋하는 상황

- Isolaction level

|level|설명|막을 수 있는 동시성 문제
|---|---|---|
|Read Uncommitted (level 0)|T1이 처리중인 데이터를 commit하기 전에 T2가 읽기 가능|없음|
|Read Commited (level 1)|T1이 처리중인 데이터는 commit을 해야 T2가 읽기 가능|Dirty Read|
|Repeatable Read (level 2)|T1이 읽은(select) 데이터는 T1이 커밋하기전까지 T2는 수정/삭제(update/delete) 불가|Dirty Read, Unrepeatable Read|
|serializable (level 3)|T1이 읽은(select) 데이터는 T1이 커밋하기전까지 T2는 삽입/수정/삭제(insert/update/delete) 불가|Dirty Read, Unrepeatable Read, Phantom Read|


- 공유락 shared lock: read only lock
- 배타락 exclusive lock: read&write lock
- lock 설정 범위
  - row, column, table, database
- dead lock
  - 두개에 트랜잭션이 각기 락을 설정하고 있고 서로 락을 건 데이터에 접근하려 해 작업을 진행 못하는 상태


- 2 phase lock
- timestamp ordering
- validation
- MVCC



### Recovery
- undo
- redo
- snapshot


# 복제 replication


# 파티셔닝

# 샤딩


# cache
------
## NonRDB & NoSQL Intro


------