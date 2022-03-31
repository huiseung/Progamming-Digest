# 목차
- [목차](#목차)
- [Computer Network](#computer-network)
- [OSI 7 layer와 TCP-IP](#osi-7-layer와-tcp-ip)
- [Application Layer](#application-layer)
  - [browser](#browser)
    - [web page](#web-page)
  - [URL과 URI](#url과-uri)
  - [DNS](#dns)
    - [browser에 domain name을 입력할때 DNS가 처리하는 과정](#browser에-domain-name을-입력할때-dns가-처리하는-과정)
  - [http](#http)
    - [version](#version)
    - [request](#request)
      - [method](#method)
    - [response](#response)
      - [status](#status)
  - [https](#https)
  - [REST API](#rest-api)
  - [browser가 web page를 띄우는 과정](#browser가-web-page를-띄우는-과정)
  - [cookie](#cookie)
  - [session](#session)
  - [proxy server](#proxy-server)
  - [reverse proxy server](#reverse-proxy-server)
  - [CDN](#cdn)
- [Transport Layer](#transport-layer)
  - [socket](#socket)
  - [TCP](#tcp)
    - [다중화와 역다중화 multiplexing and demutiplexing](#다중화와-역다중화-multiplexing-and-demutiplexing)
    - [3 hand shake](#3-hand-shake)
    - [4 hand shake](#4-hand-shake)
  - [UDP](#udp)
  - [SSL](#ssl)
- [Internet Layer(Network Layer)](#internet-layernetwork-layer)
  - [IP와 port](#ip와-port)
  - [Routing](#routing)
  - [ARP](#arp)
  - [subnet mask](#subnet-mask)
- [Network Access Layer(Data Link Layer + Physical Layer)](#network-access-layerdata-link-layer--physical-layer)
  - [MAC](#mac)
- [apache 와 nginx](#apache-와-nginx)
- [tomcat](#tomcat)
- [취약점](#취약점)
  - [xss](#xss)
  - [csrf](#csrf)
- [proxy](#proxy)
- [reverse proxy](#reverse-proxy)
- [load balancing](#load-balancing)


# Computer Network
- internet: inter network에 줄인말, 전세계 computer가 연결되어 data를 주고 받는 system
- protocol: internet에서 data를 주고 받을 때 지키는 약속
  - 어떻게 보낼까
  - 어떻게 응답할까
  - 응답이 없을때 어떻게 대응할까
- node: internet에서 data를 주고 받는 주체인 하드웨어를 부르는 말
- host: application 기능이 있는 node


# OSI 7 layer와 TCP-IP
- Computer Network System을 구성하는 요소들을 구분하는 방식

![](./image/OSI.PNG)

# Application Layer
- internet에서 다른 computer와 통신하는 기능을 갖춘 프로그램
  - http
    - hyper text transper protocol
    - web server와 web client(browser)사이에 통신 프로토콜
  -  ftp
     - file transper potocol
     - 파일 통신 프로그램
  - stmp
    - simple mail transfer protocol
    - 문자 통신 프로그램
- web
  - world wide web을 줄여서 www 혹은 web이라 부른다
  - 수 많은 web page와 web server가 연결된 연결망
  - internet에 부분집합

- client-server 구조
- p2p 구조
- client: 다른 프로세스와 접속을 시도하는 프로세스
- server: 다른 프로세스의 접속을 기다리는 프로세스


- socket: 애플리케이션과 트랜스포트 계층간 인터페이스
  - 애플리케이션에선 다음에 소켓 제어를 할 수 있다
    -  트랜스포트 프로토콜 선택 TCP or UDP
    -  최대 버퍼, 최대 세그먼트 크기
  - 송신측 소켓은 애플리케이션이 생성한 메세지를 네트워크로 전송하는 책임이
  - 수신측 소켓은 받은 메세제를 애플리케이션에 이동시키는 책임이 있다
-  

## browser
- web client
- html를 이용해 만든 web page가 돌어가는 프로그램
### web page
- html
  - hypter text markup language
  - web page를 만들 때 사용하는 기본 언어
  - 좀더 복잡한 웹페이를 만들기 위해 css, js 언어를 사용한다
- web page
  - web상에 존재하는 문서
  - web server에 저장되며, browser에서 검색(요청)시 응답되어 rendering(화면에 띄운다)
  - hypter link을 이용해 다른 web page로 이동 가능하다
- web site
  - 여러 web page에 묶음


## URL과 URI
- uniform resource indentifier
- web상에 resource를 식별하는 형식

- uniform resource locator
- web상에 resource 위치를 가리킨다

- uri는 url 보다 큰 개념
- protocol://domain:port/path?query#fragment 로 구성된다


## DNS
- domain name system
- ip주소를 사람이 읽을 수 있는 이름(domain name)으로 등록해 둔 계층형 데이터 베이스 시스템
- internet 상에 모든 domain은 root부터 시작해 . 으로 구분되는 계층 형태로 저장된다


- ip주소는 host가 네트워크 어디에 위치하는지 나타낸다
- ip주소는 32bit다
- ip주소는 4계층으로 이루어져있다
- ip주소의 한 계층은 0~255 십진수로 표현한다(8bit)
- ip주소에 계층은 . 으로 구분한다



### browser에 domain name을 입력할때 DNS가 처리하는 과정
1) broswer가 local dns(내 컴퓨터에 저장되어있는 파일)에 domain name에 맞는 ip주소를 찾는 query를 보낸다
2) local dns에서 해당 domain name에 맞는 ip 주소가 있으면 반환, 없으면 local dns는 root dns로 해당 domain name을 관리하는 dns를 찾기 위한 query를 보낸다
3) 해당 domain name을 관리하는 dns가 나올때까지 query를 주고받는다
4) 해당 domain name을 관리하는 dns로 부터 ip주소 응답
5) local dns는 해당 ip주소와 domain name을 캐싱하고 browser에 응답
6) browswer는 찾은 ip주소를 갖는 server와 연결을 시도한다 

------

## http
- hyper text transper protocol
- web server와 web client(browser)사이에 통신 프로토콜
- request: client가 server에 보내는 요청
- response: server가 client에 보내는 응답
- stateless
  - server는 client에 상태를 저장하고 있지 않다
  - request에 현재 client에 상태를 담아야 한다 
- connectionless
  - 응답을 처리하고 난 후 client와 연결을 끊는다

### version
- 0.9
  - 1991년 발표
  - get method만 있다
  - header가 없다
- 1.0
  - 1996년 발표
  - method 추가
  - header 추가
  - content-type이 헤더에 있어 html외에 다른 형식 주고 받을 수 있다
  - 응답에 status code가 담긴다
- 1.1
  - 1997년 발표
  - TCP 기반 연결
  - 한번 요청을 응답하더라도 header 부분에 keep alive 필드값을 이용해 연결(hand shake)를 해제하지 않고 재사용한다
  - 요청에 응답이 오지 않더라도 다음 요청을 보내는 pipelining 기능 추가
- 2.0
  - 2015년 발표
  - TCP 기반 연결
  - multiplexed stream을 이용해 하나에 연결에서 여러 응답/요청을 동시에 주고 받을 수 있다
- 3
  - 개발 진행중
  - UDP 기반 연결
  - QUIC


### request

![](./image/request.PNG)

1: method
2: version
3: header
4: request body, get은 request body가 없다

#### method
| method | 의미 |
|---|---|
| get | |
| post | |
| delete | |
| put | |
| patch | |
| options | |
| header | |

### response

![](./image/response.PNG)

version
status
header
response body

#### status
|status|의미|
|---|---|
| 200 | 정상 응답 |
| 400 | bad request |
| 401 | unauthorized, 로그인 하지 않음(영단어를 해석하면 권한 없음이지만 실제론 인증되지 않음으로 사용한다 ) |
| 403 | forbidden, 권한 없음 |
| 405 | method not allowed |
| 500 | server error |

## https

1) client가 ssl로 암호화된 페이지 요청
2) server가 client에게 인증서 전송
3) client가 

## REST API

## browser가 web page를 띄우는 과정
- web page는 uri로 지정할 수 있는 파일들(html, jpeg, 등)로 구성된다

1) 주소창에 url이 입력된다
2) 서버로부터 html 파일을 받아온다
3) html 내용을 기반으로 DOM Tree 생성
4) html 파싱 과정중 script 태그를 만나면 자바스크립트 엔진을 실행해 해당 과정이 끝날때까지 파싱 중단, 사용자 경험을 위해 script 태그를 body 끝에 두는걸 권장
5) html 파싱 과정중 css 파일을 다운받는 태그를 만나면 다운로드 진행, header에 link 태그로 있는 편이다
6) DOM Tree가 완성되면 css파일을 바탕으로 CSSOM Tree 생성
7) DOM Tree와 CSSOM Tree를 이용해 Render Tree 완성
8) Render Tree를 바탕으로 레이아웃 잡기
9) 브라우저에 페인트
  
## cookie
- web site가 사용자 정보를 추적하기 위해 저장해두는 정보 
- server가 response header에 'set-cookie' filed로 담은 value를 browser가 저장한다
- request 보낼시 cookie는 header에 담긴다


## session
- session: web server에 저장됨


## proxy server
- response cache 역활
- client가 요청을 보낼 경우 proxy server는 response가 cache되어 있는지를 확인하고 있다면 이를 응답한다, 없다면 server에 요청을 보내 response를 받아 와 저장한 후 client에게 응답한다
- 비슷한 목적으로 사용하는 여러 client들을 하나에 proxy server로 request 보내게끔 시스템을 설계하면 server로 직접 가는 트래픽을 줄일 수 있다
- caching시 마지막 수정된 날짜를 함께 저장한다
- client는 cache에서 response를 받기전 server에게 조건부 get를 보내 수정 사항이 있는지 확인(status 304 response)를 확인 받고 cache에서 받는다

## reverse proxy server

## CDN


--------

# Transport Layer
- 프로세스간 논리적 통신 제공: 서로 다른 호스트에서 동작중인 애플리케이션이 직접 연결된 것처럼 통신한다.
  - 실제론 호스트와 호스트 사이에 수많은 라우터와 링크들이 존재한다
- segment: transport layer에 packet 단위
  - 애플리케이션 message를 분해한 후 header를 붙인다 
  - 이를 송신측은 Network layer로 보내고, 수신측은 Network layer로 부터 받는다
- data 전송, 통신 제어
  - 신뢰, 처리율, 처리시간 보장, 보안을 담당한다
- IP(Internet Protocol)은 segment 전달을 보장하지 않는다.
  - 네트워크에서 전송중인 data packet은 손실될 수 있다. 순서 역시 보장하지 않는다
  - ex) 라우터 버퍼 오버플로우, 비트 조작
- 오디오, 비디오같은 data는 어느 정도(사용자가 이상을 느끼지 않을 정도) 손실을 허용한다
- 처리율: 초당 처리 bit 
- 게임과 같이 실시간 통신이 중요한 경우 처리 시간이 짧아야 한다
  - nagle's algorithm
-  

## socket
- 하나에 computer에 동작중인 process가 다른 computer에 동작 중인 process와 통신하기 위해 OS가 제공하는 기능(system call을 이용해 구현) 
- 프로세스에서 네트워크로 데이터 송신, 네트워크로부터 프로세스로 데이터 수신을 하는 출입구 역활
- TCP와 UDP 두가지 방식이 있다

## TCP
- transmission control protocol
- 프로세스간 논리적 통신
- 연결 지향형(3 hand shake와 4 hand shake)
  - 하나에 client와 하나에 server를 점대점으로 연결한다
- 신뢰도 높음(순서대로 모두 도착함을 보장한다)
- 혼잡 제어 있음
  - 통신중인 호스트 사이에 있는 스위치와 링크가 폭주하는젓을 방지
- 오류 검출 가능
- 타이머

![](./image/tcp_segment.PNG)

![](./image/segment_seq_ex.PNG)

- 순서번호 SEQ
- 확인응답번호 ACK: 

### 다중화와 역다중화 multiplexing and demutiplexing
- network layer에 호스트간 논리적 통신을 프로세스간 논리적 통신으로 확장
- 다중화: 송신을 위해 message를 segment로 만들고 이를 network layer로 보내는 과정
- 역다중화: transport layer에 수신받은 segment를 올바른 socket에 전달하는 과정

### 3 hand shake
- TCP socket을 쓰는 client-server에 통신 연결 과정
- 

### 4 hand shake
- TCP socket을 쓰는 client-server에 통신 종료 과정

## UDP
- user datagram protocol
- 비연결형
- 신뢰도 낮음(송신한 데이터가 목적지에 제대로 도착함을 보장하지 않는다)
- 혼잡 제어 없음
- 프로세스간 논리적 통신, 오류 검출 두 가지만 제공

## SSL
- secure socket layer
- TCP, UDP 둘다 보안 기능이 없다
- 

------
# Internet Layer(Network Layer)
- host간에 논리적 통신 제공
## IP와 port
- ip: internet 서비스 공급자에서 할당 해준 internet에서 computer를 구분하는 주소 
  - version 4는 . 으로 구분되는 4개의 숫자로 이루어져있다. 각 숫자는 0~255(8bit)범위를 갖는다. 총 32bit
  - 한 computer에 ip는 공급자에 의해 바뀔 수 있다
- port: 한 computer(동일 ip)에서 process를 구분하는 번호

## Routing

## ARP

## subnet mask


------
# Network Access Layer(Data Link Layer + Physical Layer)
## MAC
- media access control
- 하드웨가 갖고 있는 고유 주소
- : 으로 구분되는 6개 숫자로 이루어져 있다. 각 숫자는 32bit로 16진수 2자리다  
- ip와 다르게 변하지 않는다


------
# apache 와 nginx
- apache: thread 기반, 요청이 올때마다 thread 생성
- nginx: 최소 thread만으로 event queue


# tomcat



-------

# 취약점
## xss

## csrf

---------

# proxy
- client대신 request를 보내고 response 받아 caching한다


# reverse proxy
- server 대신 request를 받아 server가 처리하게 한후 response을 대신 보낸다
- load balancing

----------

# load balancing
