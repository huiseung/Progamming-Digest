# 목차
- [목차](#목차)
- [Operating System](#operating-system)
- [CPU virtualization](#cpu-virtualization)
  - [process와 limited direct execution](#process와-limited-direct-execution)
  - [scheduling 과 context switching](#scheduling-과-context-switching)
- [memory virtualization](#memory-virtualization)
  - [paging](#paging)
  - [thread](#thread)
  - [lock, conditaion variable, semaphore](#lock-conditaion-variable-semaphore)
  - [JVM](#jvm)
- [persistence](#persistence)
  - [Input Output](#input-output)
  - [RAID](#raid)
  - [file system](#file-system)
  - [FSCK and Journaling](#fsck-and-journaling)
  - [Recovery](#recovery)

# Operating System


# CPU virtualization
## process와 limited direct execution
Q. 프로레스란 무엇인가요
- 코드(명령어) 모음을 프로그램이라 부르며, 실행 중인 프로그램을 프로세스라 부른다

Q. 프로세스는 어떤 구조를 가지고 있나요
- 프로그램이 실행중이란 건 disk에 있던 파일이 메모리에 적재되어 CPU에 의해 코드가 동작중인 상황을 의미한다.
- 메모리 적재시 4가지 영역으로 구분한다
  - code: 컴파일된 소스코드가 저장되는 영역
  - data: static variable이 저장되는 영역
  - stack: local variable, return address가 저장되는 영역
  - heap: dynamic allocation data가 저장되는 영역

Q. PCB, Process Control Block 란 무엇인가요
- 운영체제가 프로세스를 관리하기 위해 필요한 정보를 저장한 자료구조
- proc
  - pid
  - parent process
  - memory stat pointer
  - memory size
  - kernel stack bottom pointer
  - open file
  - current directory
  - trap frame
  - context
  - state

- context
  - ip: 다음 수행될 명령어가 저장된 주소를 가르키는 register
  
  - sp: stack 영역에서 최상단 주소를 가르키는 register
  - bp: stack 영엑에 바닥 주소를 가르키는 register
  - si: 복사 혹은 비교 대상이 저장되는 register
  - di: 복사 혹은 비교 타켓이 저장되는 register
  
  - ax: 산술 연산에 사용해, 함수 반환값을 저장되는 register
  - bx: 배열 인덱스 저장되는 register
  - dx: IO주소를 가르키는 resgister
  
  - cs: code segment를 가르키는 register
   
- state
  - runnable, running, zombie, sleeping


Q. 제한적 직접 실행이란 무엇인가요
- user mode에서는 시스템 자원에 접근 제한을 두고, 사용을 원할 경우 system call을 호출해 kernel mode에 진입하여 시스템 자원을 사용하고 작업을 마치면 user mode로 다시 돌아오게끔 설계한 방식
- kernel mode 진입시(인터럽트 발생시) 프로세스에 할당되었던 cpu를 반납받아 스케줄링을 작업할 수 있다.
- 시스템 자원 접근에 보안성을 높일 수 있다.

Q. 멀티 프로세스 다른 말로 CPU 가상화는 어떻게 가능한가요.
- 프로세스에 할당한 CPU를 반납 받고 다른 프로세스에게 할당하는 행위를 아주 짧은 시간동안 반복하여, 프로세스들 입장에서 마치 여러대의 CPU가 존재하는 것처럼 가상화시키는 기술
- CPU 반납은 타임 인터럽트를 발생시켜 이루어진다. 
  - 타임 인터럽트: 부팅시점부터 시스템 종료까지 주기적으로 발생시키는 인터럽트

Q. 인터럽트란 무엇인가요
- 입출력 연산, 예외상황 등에 작업을 마이크로프로세서에게 기존 시스템 수행흐름을 중지시키고 먼저 처리를 요청, 처리가 끝나면 기존 작업으로 돌아간다
- software interupt
- hardware interupt

Q. 시스템콜이란 무엇인가요
- 커널 접근 인터페이스, 응용프로그램이 시스템 자원을 사용하길 원할때 유저모드에 있는 프로그램을 커널모드로 바꾼다. 작업을 마치면 유저모드로 돌아온다. 

Q. IPC, 프로세스간 통신은 어떻게 이루어 지나요
- 서로 다른 프로세스간 통신
- 익명 PIPE
  - 동일한 부모를 가진 프로세스간 통신
- Named PIPE
  - 
- Mesage Queue
- Shared Memory
- Memory Map
- Socket

## scheduling 과 context switching

Q. 스케줄링이란 무엇인가요
- context switching시 다음 동작할 process를 결정하는 과정

Q. 컨텍스트 스웨칭이란 무엇인가요
- 현재 kernel stack, 


Q. 선점 스케줄링과 비선점 스케줄링
- CPU를 프로세스로 부터 갈취해 다른 프로세스에게 할당하는 방식으로 동작하는 스케줄링이면 선점, 아니면 비선점 스케줄링
- 비선점 스케줄링에 대표로 FIFO
- 선점 스케줄링에 대표로 RR, MLFQ

Q. RR 스케줄링은 어떻게 동작하나요 

Q. MLFQ 스케줄링은 어떻게 동작하나요



# memory virtualization
##  paging
Q. 메모리 가상화가 무엇인가요
- 여러 프로세스를 동시 실행시키기 위해선 CPU 가상화뿐 아니라 메모리 가상화도 필요하다
- 주소 변환: 프로세스에서 사용중인 메모리 주소와 실제 메모리 주소를 대응시키는 기술
  - 실제 메모리 주소 = 가상 주소 + base register에 저장된 값
  - 영역 사이즈 = limit register - base register 
- 프로세스는 다른 프로세스의 메모리 공간에 침입하지 않게끔 OS가 보장해 주어야 한다

Q. paging 기법을 설명해 주세요
- 외부단편화 현상을 막기 위해 메모리 공간을 고정 크기로 나누어 주소 변환을 진행한다
- 하나에 고정 크기 영역을 page라 부른다
- 프로세스의 page가 실제 메모리에 어디 위치하는지 관리하는 page table이 존재한다

Q. TLB, Translation Lookaside Buffer에 대해 설명해 주세요
- TLB는 VPN(가상 페이지 번호)과 PFN(실제 페이지 번호) 대응 관계를 CPU가 빠르게 파악하기 위해 page table에 접근하기전 확인해 보는 캐시다.
- TLB에 원하는 페이지 정보가 있다면 반환하고, 없다면 페이지 정보를 TLB에 등록한다
- 등록하는 과정에서 TLB가 저장공간이 가득 차 있다면 하나를 제거해 교체한다.

Q. page 교체

Q. LRU 교체 정책
- 가장 사용되지 않았던 page를 교체시키는 정책
- 구현 자료구조: double linked list와 hashtable을 결합해 구현


## thread
Q. 스레드란 무엇인가요
- code, data, heap 영역을 공유하며, stack 영역만을 개별로 갖는 Light weight process
- 공유하는 주소공간에 대해서 page 교체를 할 필요가 없음
  - context switching시 process보다 효율적!
- 하나에 task를 동시 작업 가능

## lock, conditaion variable, semaphore
Q. critical section, race condition, mutual exclusion에 대해 설명해 주세요
- critical section(임계 영역): 스레드가 공유하는 자원에 접근하는 코드 구간
- race condition(경쟁조건): 다수의 스레드가 임계 영역에 동시 접근해 실행 순서에 따라 문제를 발생시킬 수 있는 상황
- mutual exclusion(상호배제): race condition이 발생하지 않도록 하나에 스레드가 공유 자원에 접근 중일때 다른 스레드가 접근 하지 않도록 막는 기술

Q Reentrant
- 여러 스레드가 동시에 접그냏도 언제나 같은 실행 결과를 보장한다는 의미 

Q. spin lock

Q. semaphore

Q. DeadLock(교착상태)이 무엇인가요
- 스레드들이 서로에 자원을 사용하기를 무한히 기다려 다음 task로 진행 못하고 있는 상황

Q. 교착상태가 발생하는 상황은 무엇인가요
- 다음 4가지가 모두 충족될때 교착상태가 발생한다
  - 상호 배제: 하나에 스레드가 자원을 독점 중
  - 점유와 대기: 자신이 가진 독점 자원을 놓치 않은 상태로 다른 자원을 요구중인 상황
  - 비선점: 스레드 스스로 독점 자원을 놓기 전까지 강제로 다른 스레드가 가져올 수 없음
  - 환영 대기: 스레드가 각기 독점중인 자원을 서로 원하며 맞물린 상태

Q. 데드락 해결
- 발생 조건 4가지 중 하나라도 해결하면 된다

Q. 동기화 문제
- 식사하는 철하자 문제
- 생산자 소비자 문제
- 독자 저자 문제

Q. Sychronous vs asychoronus
- callback function: 다른 함수에 인자로 넘길 수 있는 함수면서 어떤 
- synchronous
- asynchronous

Q. blocking vs non blocking
- blocking: A함수가 B함수를 호출할때 A함수에 제어권을 B함수에게 넘겨줌
- non blocking: A함수가 B함수를 호출할때 A함수에 제어권을 B함수에게 넘겨주지 않음

## JVM
Q. java가 os독립적으로 코드를 실행시킬 수 있는 이유


# persistence
## Input Output

## RAID
- Redundant Array of Independent Disks

## file system
Q. inode block, data block

Q. hard link와 symbolic link

Q. file access permission

## FSCK and Journaling 

## Recovery
