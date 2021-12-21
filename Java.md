- 문법을 공부하는 이유는 효율적인 코드 작성을 위해서이다.
  - 효율적인 코드 필요 조건
    - 제대로 동작한다
    - 빠르게 동작한다
    - 다른 사람도 알아 보기 쉽다
    - 수정, 확장이 쉽다


# 목차
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
    - [Anotation](#anotation)
- [Exception](#exception)
  - [try-finally보다는 try-with-resources를 사용하자](#try-finally보다는-try-with-resources를-사용하자)
  - [NullSafe](#nullsafe)
    - [Optional](#optional)
- [java.lang](#javalang)
  - [String, StrinbBuilder, StringBuffer](#string-strinbbuilder-stringbuffer)
- [Collections](#collections)
- [Stream](#stream)
- [Lambda](#lambda)
- [Thread](#thread)




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
  - 클래스와 메서드 동시에 붙인다. 해당 클래스에는 속성이 없다. 메서드를 선언만 하고 구현은 자식 클래스에서 오버라이딩
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
### Anotation


------
# Exception
## try-finally보다는 try-with-resources를 사용하자


## NullSafe
### Optional


# java.lang
## String, StrinbBuilder, StringBuffer


# Collections

# Stream

# Lambda

# Thread