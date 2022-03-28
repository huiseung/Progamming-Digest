# 목차
- [목차](#목차)
- [OSI 7 Layer 와 TCP/IP Layer](#osi-7-layer-와-tcpip-layer)
- [Application Layer](#application-layer)
  - [DNS](#dns)
- [Transection Layer](#transection-layer)
- [Internet Layer](#internet-layer)
- [Network Access Layer](#network-access-layer)
- [대용량 트래픽 처리](#대용량-트래픽-처리)

# Internet
- Internet: Inter-Network에 줄인말, 전세계 PC를 연결해 data를 주고 받는 시스템
- Protocol: Internet상에서 data를 주고받을때 지키는 체계
- Node: Internet상에서 data를 주고받는 하드웨어 주체
- Host: 애플리케이션 실행 기능이 있는 Node

# 통신 계층
![통신계층](./image/통신계층.PNG)

# 유니케스트, 멀티케스트, 브로드케스트
- 유니케스트: 1:1 통신
- 멀티케스트: 1:N(특정그룹) 통신
- 브로드케스트: 1:전체 통신

---
# Application Layer
- 전송 단위: Data
- HTTP, HTTPs, FTP, DNS
- client-sever와 p2p 구조가 있다

## HTTP
- client-server
- stateless
- connectionless
  - 요청/응답 주고 받으면 연결을 끊는다. 매 요청시마다 새로 연결을 맺는다

- request
  - header
    - URI(정형화된 자원 식별자)
      - protocal://host:port/resource_path/path_variable/query_parameter
    - method
      - get
      - post
      - put
      - delete
    - version 정보
      - 1.1
        - TCP 
        - text 통신
        - handshake 연결 재사용(keep alive)
      - 2
        - TCP
        - binary frame 통신
        - stream
      - 3
        - UDP 기반에 QUIC
    - content-type
      - application/json
  - request body
- response 
  - status
    - 200
    - 400: bad request
    - 401: unauthorized, 로그인 하지 않음(영단어를 해석하면 권한 없음이지만 실제론 인증되지 않음을 의미)
    - 403: forbidden, 권한 없음
    - 405: method not allowed
    - 500: server error
  - response body

## RESTful API
-
-

## HTTPS
- SSL 동작 순서
  - ?

## DNS
- Domain Name System
- IP를 사람이 이해할 수 있는 문자열에 대응시켜놓은걸 저장해 놓은 시스템


## 브라우저 렌더링 동작 원리
1) 주소창에 url이 입력된다
2) 서버로부터 html 파일을 받아온다
3) html 내용을 기반으로 DOM Tree 생성
4) html 파싱 과정중 script 태그를 만나면 자바스크립트 엔진을 실행해 해당 과정이 끝날때까지 파싱 중단, 사용자 경험을 위해 script 태그를 body 끝에 두는걸 권장
5) html 파싱 과정중 css 파일을 다운받는 태그를 만나면 다운로드 진행, header에 link 태그로 있는 편이다
6) DOM Tree가 완성되면 css파일을 바탕으로 CSSOM Tree 생성
7) DOM Tree와 CSSOM Tree를 이용해 Render Tree 완성
8) Render Tree를 바탕으로 레이아웃 잡기
9) 브라우저에 페인트

## DOM 
- Document Object Model
- html 조작 인터페이스

## Virtual DOM
- 페이지에 일부 내용만 바뀌었는데 브라우저 렌더링 동작 원리 과정을 처음부터 진행하는건 비효율적이다.
- 같은 부분은 유지한체 바뀐 부분만 Tree 에서 대체 하고자 나온 개념 

## 브라우저 주소창 uri 입력시 동작하는 과정
1) uri 파싱
2) https 처리
3) DNS에서 IP 가져오기, 클라이언트쪽에 저장 정보가 없다면 DNS server(53번 포트)로 request해 응답받기
4) 해당 서버와 연결 시도(3 handshake)
5) 서버로 부터 uri에 대응하는 html 파일 요청/응답
6) 브라우저 렌더링
7) TCP 연결 종료

## 쿠키
- http 통신시 자동으로 따라 붙는 키:값 자료구조

## session storage
- tab 단위 브라우저 저장 공간

## local storage 
- 창 단위 브라우저에 저장 공간


## CORS 와 preflight request
- 브라우저는 동일 도메인에 위치한 리소스 접근을 허락하도록 설계되어 있다(Same Origin Policy)
- CORS(Cross Origin Resource Sharing)은 다른 도메인에 리소스에도 접근 가능하도록 하는 메커니즘
- prefilight request는 실제 요청 전 요청이 가능한지 확인용 요청이다

## XSS(Cross Site Scipting)
- 웹 사이트에 악의적 스크립트를 삽입

## CSRF(Cross Site Request Forgery)
- 사용자 권환을 이용해 서버에 특정 요청을 하는 공격

---
# web server와 WAS 
- web server: 정적 컨텐츠 제공 서버
- was: 동적 컨테츠 제공 서버

# forward proxy, reverse proxy
- 클라이언트와 서버간 통신을 대리로 수행하는 서버를 proxy라 부른다
- forward proxy 
  - 클라이언트가 서버에 보내는 요청을 대신 전달해 응답이 오면 클라이언트에 전달한다
  - 캐싱 기능을 갖출 수 있다
  - 서버는 클라이언트 IP를 알 수 없다
- reverse proxy
  - 클라이언트에게서 온 요청을 서버 대신 받고 서버에게 전달해 응답을 받은 후 이를 클라이언트에게 반환한다
  - 로드 밸런싱 기능을 갖출 수 있다
  - 클라이언트는 서버 IP를 알 수 없다

# L7 Load Balancer
- Nginx
  - thread 기반
- apache
  - process기반


# CDN
- Content Delivery Network
- static 컨텐츠를 빠르게 응답하기 위해 분산 시켜 놓은 서버
- ex) S3

# Web Socket
- 실시간으로 연결을 끊을때까지 지속한다
- 서버쪾에서 클라이언트에게 연결을 시도

------
# 대용량 트래픽 처리법
- 발생시점을 예상 가능한가 vs 급작스런 증가인가
- 캐시에서 감당이 안 될때 디비에서 처리하도록 서킷브레이커
- 스케일업
- 스케일아웃
- 스프링이면 톰캣 스레드풀 설정 바꾸기
- 로드밸런싱
- 데이터베이스 샤딩
- 데이터베이스 레플리카
- 캐시
- CDN
- 그럼에도 불구하고 커버가 불가능한 경우 클라이언트에게 실패 응답을 보내 다시 시도하게 끔 한다

------
# Transport Layer
- 송신된 데이터를 수신측 애플리케이션에 전달
- 전송 단위: segment
- TCP, UDP, port

|비교|TCP|UDP|
|---|---|---|
|연결방법|양방향|-|
|전송순서|보장|보장 않음|
|전송속도|느림|빠름|
|신뢰성|보장|유실을 응용프로그램에서 고려필요|


## TCP, Transmission Control Protocol
- 연결 지향형 프로토콜
- 3 way handshake
  - tcp socket이 연결을 시작하는 단계
  - server-socket이 listen()중
  1) client-scoket이 server에 connect시도, SYN(N) 전송
  2) server-socket이 이를 받아 SYN(M)+ACK(N+1) 전송
  3) client-socker이 이를 받아 ACK(M+1) 전송
  - 하고 싶었던 요청/응답 전송 진행
- data 송수신
  1) client가 server에 packet을 전송
  2) server가 client에게 ACK를 전송
  3) client가 server에게 ACK가 안 왔다면 재전송, 아니라면 다음 작업 이어 하기

- 4 way handshake
  - tcp socket이 연결을 종료하는 단계
  1) client-scoket이 server에 FIN(N) 전송
  2) server-scoket이 이를 받아 ACK(N+1) 전송
  3) server-socket가 client가 미처 보내지 못한 packet을 기다린 다음 client에게 FIN(M) 전송
  4) client-socket이 이를 받아 server에게 ACK(M+1) 전송, server closed 
- 신뢰성
  - 흐름제어
    - 송신, 수신자간 처리 속도 차이
  - 오류제어
    - checksum을 이용한 데이터 검증
  - 혼잡제어
    - 라우터에 처리 속도 부족

## 네이글 알고리즘 nagle algorithm
- 데이터 전송시 네트워를 통해 보내는 패킷수를 줄여 효율성을 높이는 알고리즘
  - ACK를 받기까지 버퍼에 대기하느라 속도는 느려지지만 전송 패킷 수는 줄어든다
  - 실시간성을 요구할땐 좋지 않다
- TCP 소켓이 기본으로 채택하고 있는 알고리즘


## UDP
- 패킷 유실될 수 있기에 응용프로그램에서 처리 필요
- 데이터 신뢰성 보단 속도를 중요시할때 이용
- 영상 송출, DNS
- connection less
- 오류 제어는 존재





```
if 새 데이터 전송
  if 상대가 받을 수 있는 사이즈 >= 세그먼트사이즈최대값 and 데이터 사이즈 >= 세그먼트사이즈
    최대 세그먼트 사이즈 만큼 전송
  else
    if 보내지 못한 데이터 부분이 남음
      ACK를 받을 때까지 버버에 넣고 대기
    else


```

## port
- 하나에 호스트에서 동작중인 여러 프로세스를 구분하는 번호

## L4 Load Balancing
- L4는 OSI 7 layer중 4번째 계층인 transport layer를 의미 
- IP와 port를 이용한 부하 분산


------
# Internet Layer
- 수신측 까지 데이터를 전달
- 전송단위: packet
- IP, ARP, ICMP

## IP address
- 통신망에서 host를 구분하는 번호
- 변동 가능성 존재
- IP 클래스
  
|class|IP주소|앞자리 범위|서브넷 마스크|네트워크 갯수|각 네트워크 별호스트 갯수|
|---|---|---|---|---|---|
|A|0sss ssss.hhhh hhhh.hhhh hhhh.hhhh hhhh|0~127|255.0.0.0|2^7, 첫 옥탯이 네트워크 영역 주소|2^24-2
|B|10ss ssss.ssss ssss.hhhh hhhh.hhhh hhhh|128~191|255.255.0.0|2^14, 두번째 옥탯까지가 네트워크 영역 주소|2^16-2
|C|110s ssss.ssss ssss.ssss ssss.hhhh hhhh|192~223|255.255.255.0|2^21, 세번쨰 옥탯까지가 네트워크 영역 주소|2^8-2|


- 서브네팅
  - IP 주소를 네트워크 영역+호스트영역으로 구성
  - 같은 네트워크 영역내 IP끼리는 라우터를 거치지 않고 패킷 교환 가능
- 서브넷마스크
  - 연속된 1과 그다음 연속된 0으로 된 32 자리 2진수
  - 1은 네트워크 영역을 0은 호스트 영역을 의미
  - Class에서 제공하는 서브넷 마스크 말고 자체 서브넷 마스크를 이용할 경우 IP주소를 표기할때 뒤에 '/1에갯수' 를 적어 해당 IP의 서브넷 마스크를 나타낸다
  - IP주소 AND 서브넷마스크 = 네트워크 영역
  - 가질 수 있는 서브넷 네트워크 수와 각 서브넷 네트워크에 호스트 영역 수를 계산할 줄 알아야 한다
  - 호스트 수는 2^(영역수)-2, 빼기 2는 네트워크 영역 구분용(영역내에 가장 작은 수)과 브로드캐스트 용(영역내 가장 큰 수)


## Routing
- 두 호스트간 데이터를 주고 받을때 경로를 결정하는 방법론

## mask, subnet

## ARP 
- IP 주소를 MAC 주소로 변환하는데 사용하는 프로토콜
- 브로드 캐스트를 통해 해당 IP주소를 가진 host를 찾음

------
# Network Access Layer
- Ethernet, MAC address, casting

## MAC address
- 통신망에서 하드웨어 식별 번호
- 변하지 않는다

------