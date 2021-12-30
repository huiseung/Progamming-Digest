# Docker란 무엇이고 왜 사용할까
- docker는 컨테이너 가상화 기술이다
- 서버에 프로그램을 배포할시 환경설정을 고정하여 배포할 수 있다.
- 하나에 서버에 여러 프로그램을 배포&실행시킬때 서로 독립적인 환경을 구축할 수 있다

# 설치
## 윈도우 도커 환경 설정
- docker 환경변수 등록
  - C/ProgramFiles/Docker/Docker/resources/bin 을 등록
  - docker.exe가 있는 폴더 위치를 등록해야 커멘드에서 docker 명령어 사용가능
## ec2에 도커 설치하기
1) sudo yum install docker
2) sudo systemctl start docker
3) sudo docker pull 


# 기본 명령어
## 터미널로 도커 실행/종료
- sudo systemctl start docker 
- sudo systemctl stop docker
## docker image
- image 전체 조회
  - docker image ls
- hub에서 image 검색
  - docker search 이미지이름
- hub에서 image 다운로드
  - docker pull 이미지이름:태그
- image 삭제
  - docker rmi -f 이미지이름
  - docker rmi -f $(docker images -q --filter "reference=앞에키워드*")
    - 이름 비슷한 여러 이미지를 한번에 지우고싶을때 이용
- image 만들어 hub에 등록하기

## docker container
- container 전체 조회
  - docker container ls -a
  - 출력 정보
    - 컨테이너 아이디, 이미지이름, 생성일자, 상태, 컨테이너이름
- container 생성및 실행
  - docker run -d --name 컨테이너이름 --network 네트워크이름 -e 환경설정 -p 호스트포트:컨테이너포트 이미지이름:태그
  - -d는 데몬모드, 백그라운드 실행을 의미
- container 중지
  - docker stop 컨테이너이름
- container 시작
  - docker start 컨테이너이름
- container 삭제
  - docker rm 컨테이너이름 
- container에 명령어창 띄우기
  - docker exec -it 컨테이너이름 bash

## docker network
- network 전체 조회
  - docker network ls
- network 상세 조회
  - docker network inspect 네트워크이름
  - Conainers에 해당 네트워크를 이용하는 컨테이너 정보를 알 수 있다.
- network 생성
  - docker network create 네트워크이름
- 실행중인 container에 network 연결하기
  - docker network connect 네트워크이름 컨테이너이름

## docker-compose.yml과 Dockerfile
- Dockerfile이란 기존 이미지를 기반으로 커스텀 이미지를 만드는 파일을 의미한다

- image 만들기
  - docker build 이미지만들경로 -f 도커파일경로 -t 만들이미지이름

- Dockerfile기반으로 image를 만들고 container를 생성및 실행
  - docker-compose up -d 

```
version: "3"
services:
  이름:
    build: 도커파일경로
    container_name: 컨테이너이름
    ports:
      - "호스트포트:컨테이너포트"
    networks:
      - default
      - 네트워크 이름
    depense_on:
      먼저 실행할 이름

networks:
  네트워크이름:
    driver: bridge


```

### Dokerfile 문법
- FROM 기반이미지이름
  - 해당 이미지를 기반으로 커스텀 이미지를 만들 예정
- COPY 호스트에서_옮기고_싶은것 컨테이너에_옮기고_싶은_위치
  - 이미지에 포함될 호스트에 있는 파일을 지정 
- WORKDIR 작업경로
  - 해당 경로로 이동
- RUN 명렁어
  - 이미지가 만들어질떄 실행할 명령어 
- CMD 명령어
  - 해당 이미지로 컨테이너를 만들때 실행할 명령어
  - 실행시점에 변경 가능
- ENTYPOINT 명령어
  - 해당 이미지로 컨테이너를 만들때 실행할 명령어
  - 실행시점에 변경 불가

---
# Kubernetes란 무엇이고 왜 사용할까
- kubernetes는 도커 오케스트라 기술이다


---

# cheat sheet
## mysql docker
1) 이미지 다운로드
   - docker pull mysql:8.0.22 
   - kafka connect에서 사용하는 것과 같은 버전으로
2) 컨테이너 생성및 실행
   - dcoker run -d --name 컨테이너이름 -e MYSQL_ROOT_PASSWORD=디비비밀번호 -p 로컬에서설정한디비포트:3306 mysql:8.0.22
3) 컨테이너안에 bash 실행
   - docker exec -it 컨테이너이름 bash
4) 컨테이너안에 mysql 접속
   - mysql -u root -p
5) 컨테이너안에 mysql database 만들기
   - create database 디비이름


## spring docker
1) build로 jar 파일 만들기
   - gradlew ./bootJar

2) java image 다운로드
   - docker pull openjdk:11

3) Dockerfile 만들기
```
FROM 
COPY *.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

4) image 만들기
   - docker build -t 이미지이름

5) docker hub repository 만들어 image 올리기
- docker push 레포지토리이름

6) 데이터베이스, 카프카 등 스프링과 연동할 시스템을 담은 컨테이너들 전부 실행시키기

7) 스프링 컨테이너 실행시키기
   - 시스템들과 스프링에 컨테이너들은 같은 네트워크에 등록해야 연동 가능하다 