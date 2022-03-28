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
  - [cookie와 session](#cookie와-session)
- [Transport Layer](#transport-layer)
  - [socket](#socket)
  - [TCP](#tcp)
  - [UDP](#udp)
  - [3 hand shake](#3-hand-shake)
- [Internet Layer(Network Layer)](#internet-layernetwork-layer)
  - [IP와 port](#ip와-port)
  - [Routing](#routing)
  - [ARP](#arp)
- [Network Access Layer(Data Link Layer + Physical Layer)](#network-access-layerdata-link-layer--physical-layer)
  - [MAC](#mac)


# Computer Network
- internet: inter network에 줄인말, 전세계 computer가 연결되어 data를 주고 받는 system
- protocol: internet에서 data를 주고 받을 때 지키는 약속
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

### browser에 domain name을 입력할때 DNS가 처리하는 과정
1) broswer가 local dns에 domain name에 맞는 ip주소를 찾는 query를 보낸다
2) local dns에서 해당 domain name에 맞는 ip 주소가 있으면 반환, 없으면 local dns는 root dns로 해당 domain name을 관리하는 dns를 찾기 위한 query를 보낸다
3) 해당 domain name을 관리하는 dns가 나올때까지 query를 주고받는다
4) 해당 domain name을 관리하는 dns로 부터 ip주소 응답
5) local dns는 해당 ip주소와 domain name을 캐싱하고 browser에 응답


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

1: version
2: status
3: header
4: response body

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

## REST API

## browser가 web page를 띄우는 과정

## cookie와 session


------
# Transport Layer
- data 전송, 통신 제어
## socket
- 하나에 computer에 동작중인 process가 다른 computer에 동작 중인 process와 통신하기 위해 OS가 제공하는 기능(system call을 이용해 구현) 
- TCP와 UDP 두가지 방식이 있다

## TCP

## UDP

## 3 hand shake

------
# Internet Layer(Network Layer)
## IP와 port
- ip: internet 서비스 공급자에서 할당 해준 internet에서 computer를 구분하는 주소 
  - version 4는 . 으로 구분되는 4개의 숫자로 이루어져있다. 각 숫자는 0~255(8bit)범위를 갖는다. 총 32bit
  - 한 computer에 ip는 공급자에 의해 바뀔 수 있다
- port: 한 computer(동일 ip)에서 process를 구분하는 번호

## Routing

## ARP



------
# Network Access Layer(Data Link Layer + Physical Layer)
## MAC
- media access control
- 하드웨가 갖고 있는 고유 주소
- : 으로 구분되는 6개 숫자로 이루어져 있다. 각 숫자는 32bit로 16진수 2자리다  
- ip와 다르게 변하지 않는다
- 