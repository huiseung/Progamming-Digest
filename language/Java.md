# 언어 공부 목적
- 문법을 공부하는 이유는 효율적인 코드 작성을 위해서이다.
  - 효율적인 코드 필요 조건
    - 제대로 동작한다
    - 빠르게 동작한다
    - 다른 사람도 알아 보기 쉽다
    - 수정, 확장이 쉽다


# 목차
- [언어 공부 목적](#언어-공부-목적)
- [목차](#목차)
- [OOP](#oop)
- [Java version update 중요 사항](#java-version-update-중요-사항)
- [JVM](#jvm)
- [Keyword](#keyword)
  - [접근 제한자](#접근-제한자)
  - [그 외 키워드](#그-외-키워드)
- [Data Type](#data-type)
  - [Primitive Type](#primitive-type)
  - [Array](#array)
  - [Class](#class)
    - [Constructor Design Pattern](#constructor-design-pattern)
    - [Object Class](#object-class)
      - [equals 와 hashCode](#equals-와-hashcode)
      - [toString](#tostring)
    - [상속 Inheritance](#상속-inheritance)
    - [Enum](#enum)
  - [Generic](#generic)
  - [Interface](#interface)
    - [interface와 abstract class에 활용](#interface와-abstract-class에-활용)
    - [Anotation](#anotation)
- [Exception](#exception)
  - [상속 구조](#상속-구조)
  - [Throwable](#throwable)
  - [checked exception](#checked-exception)
  - [unchecked exception](#unchecked-exception)
  - [try-finally보다는 try-with-resources를 사용하자](#try-finally보다는-try-with-resources를-사용하자)
  - [NullSafe](#nullsafe)
    - [Optional](#optional)
- [java.lang](#javalang)
  - [String, StrinbBuilder, StringBuffer](#string-strinbbuilder-stringbuffer)
  - [Thread와 ThreadLocal](#thread와-threadlocal)
    - [Thread](#thread)
    - [ThreadLocal](#threadlocal)
- [java.util.Collections](#javautilcollections)
- [Stream](#stream)
- [Lambda](#lambda)
- [replication](#replication)


# OOP


# Java version update 중요 사항
- version 5
  - generic
- version 8
  - lambda
  - stream
  - optional
  - intenface default method
- version 9
  - module
- version 11

# JVM
- .java 파일을 javac 컴파일러를 이용해 .class 파일(Byte Code)로 바꾼다 JIT가 os 맞춤으로 바꾼다
- excutable jar 파일
  - 
- class loader
  - class파일들을 runtime data area에 적재한다
- runtime data area
  - 컴퓨터 시스템으로부터 클래스 파일 실행시 할당받은 메모리영역 
  - 5가지 용도로 구분해 사용한다. 
    - pc registers, stack area, native method stack은 스레드별로 생성해 사용한다
    - method area, heap area은 스레드가 공유하며 사용한다
  - pc registers
    - thread가 다음 실행할 명령어를 저장
  - stack area
    - thread가 메서드를 호출하면 지역변수를 저장, 메서드가 리턴되면 메모리에서 해제된다.
    - main 역시 하나의 메서드다. 
    - primitive type은 값 자체가 저장되고 reference type은 heap영역에 주소가 저장된다
  - native method stack
    - 자바 이외의 언어로 작성된 코드를 위한 영역
  - method area(static area)
    - 클래스와 관련한 정보(이름, 속성 타입, 속성 접근 제어자)
    - static 변수, 인터페이스가 저장된다
    - 런타임이 종료되기까지 유지된다
  - heap area
    - 인스턴스에 실제 값이 저장
    - GC가 관리한다
- Execution Engine
  - Garabage Collector, Interpreter, JIT Compirer 로 구성된다
  - Garabage Collector 동작 과정
    - minor GC
    - major GC


# Keyword
## 접근 제한자
- public: 전체 접근 가능
- protected: 상속한 클래스만 접근 가능
- default: 해당 패키지에서만 접근 가능
- private: 해당 클래스만 접근 가능

## 그 외 키워드
- static
  - 메모리에 할당시 처음 설정한 주속 변하지 않음
  - 속성에 붙이면 클래스 변수가 된다, 메서드에 붙이면 인스턴스 생성 없이 호출 가능(해당 메서드 안에선 인스턴스 변수를 이용 불가)

- final
  - 클래스에 붙이면 상속 불가, 속성 앞에 붙이면 변경 불가, 메서드에 붙이면 오버라이딩 불가 

- abstract
  - 인스턴스를 생성 못하고 자식에게 상속하는 걸 목적으로 하는 클래스

- synchronized
- native
- transient
- volatile
- strictfp


# Data Type
## Primitive Type

## Array

## Class
- 필드 field
- 메서드 method
  - overloading: 같은 이름에 다른 파라미터, 리턴을 갖는 메서드들를 작성하는 기술
- 생성자 constructor
  
### Constructor Design Pattern
- static factory method

- Builder
  - 매개변수가 많은 생성자에 적용.
  - 생성시 파라미터 이름에 맞춰 값을 적어, 어떤 파라미터에 해당하는 값인지 알 수 있다.

- singleton

- dependecy injection

### Object Class

#### equals 와 hashCode

#### toString


### 상속 Inheritance

### Enum


## Generic

## Interface
- 협업시 클래스에서 사용하는 메서드에 이름과 반환값을 사전에 선언해 둔것
  - 해당 인터페이스를 상속받은 클래스에 메서드는 인터페이스에서 선언한것만 있음을 약속 받음
- 다수의 인터페이스를 상속하는게 가능
- 인터페이스를 구현(implement)하는 클래스에게 인터페이스에서 선언한 메서드를 정의하도록 강제
- 약한 연결
  - 객체를 속성으로 갖는 클래스를 정의할때 자료형을 속성을 정의한 클래스로 두지 않고 인터페이스로 두면 속성 클래스 변경에 용이하다
### interface와 abstract class에 활용

### Anotation





------
# Exception
## 상속 구조
```
Object
|-Throwable
  |-Exception
    |-RuntimeException
      |-NullPointException
      |-ArithmeticException
      |-IndexOutOfBoundException
      |-...
    |-IOException
    |-SQLException
    |-...
  |-Error
    |-VirtualMochineError
      |-StackOverFlowError
      |-OutOfMemoryError
      |-...
    |-...

```
## Throwable
```java
//method
String printStackTrace();

```

## checked exception
- compile 시점에 확인 가능
- 예외 처리를 하지 않으면 컴파일 불가
- Exception 상속받은 클래스 중 RuntimeException을 제외한 모든 클래스

## unchecked exception
- compile 시점에 잡아 내지 못해 run time 도중 발생
- 개발자가 예외 처리 코드를 짜 실행 도중 발생시 처리 문을 받을 수 있게 해야한다
- RuntimeException과 Error




## try-finally보다는 try-with-resources를 사용하자


## NullSafe
### Optional


# java.lang
- import 없이도 사용가능한 기본 패키지

## String, StrinbBuilder, StringBuffer
- String은 불변 객체. thread safe하다
```java
String str = "hello";
str += "world";
// 새 주소를 할당해 "hello world" 저장, 기존 "hello"는 garbage collector에 대상
```
- StringBuffer와 StringBuilder는 가변 객체, 값이 변경되도 주소를 새로 할당하지 않는다.
- StringBuffer는 thread safe, StringBuilder는 thread safe하지 않음.
- 언제 뭘 쓸까 정리
  - String: 문자열 연산이 적고, 멀티스레드 환경
  - StringBuffer: 문자열 연산이 많고, 멀티스레드 환경
  - StringBuilder: 문자열 연산이 많고, 단일 스레드 환경 혹은 동기화를 고려하지 않아도 되는 환경

## Thread와 ThreadLocal
### Thread
- int activeCount()
- void checkAccess()
- Object clone()
- int countStackFrames()
- Thread currentThread()
- void destroy()
- void dumpStack()
- int enumerate()

- Map getAllStackTraces()
- String getName()
- int getPriority()
- void setName()
- void setPriority()


- void join()
- void resume()
- void run()
- void sleep
- void start()
- void stop()
- void suspend()
- String toString()
- void yield()

### ThreadLocal
- T get()
  - 현재 스레드에 thread-local variable copy를 리턴
- T initialValue()
  - 현재 스레드에 thread-local variable에 값 초기화
- void remove()
  - 현재 스레드에 thread-local variable 값 제거
- void set(T)
  - 현재 스레드에 thread-local variable 값 설정

# java.util.Collections
![](./image/javaCollection.PNG)

# Stream

# Lambda

# replication
