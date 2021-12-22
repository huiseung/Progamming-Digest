# 목차
- [목차](#목차)
- [문법](#문법)
  - [변수 선언과 초기화](#변수-선언과-초기화)
    - [함수 정의](#함수-정의)
  - [흐름 제어](#흐름-제어)
    - [class](#class)
    - [scope function](#scope-function)
    - [고차함수](#고차함수)
    - [람다](#람다)
    - [리시버](#리시버)
    - [확장함수](#확장함수)
    - [코루틴(Coroutine)](#코루틴coroutine)
  - [프로젝트 시작](#프로젝트-시작)

# 문법

## 변수 선언과 초기화
```kotlin
//val은 초기화 후 값 변경 불가
val a = 1
val b: Int
b = 1
val c: Int = 1

//var는 초기화 후 값 변경 가능
var a = 1
var b: Int
b = 1
b = 2
var c: Int  = 1

//null safe


//초기화 지연 lateinit 과 lazy
//lateinit, var에 사용
lateinit var a: String //a는 null이 될 수 없다, Primitive Type에는 사용 불가
a = ""

//lazy, val에 사용
val a: String by lazy {""}



```




### 함수 정의
```kotlin
/*
fun 함수이름(변수명: 자료형, 변수명: 자료형): 반환자료형{
    함수정의
}
*/

fun sum(a: Int, b: Int): Int{
    return a+b;
}

fun sum(a: Int, b: Int) = a+b
```


## 흐름 제어
```kotlin
val 변수 = if(조건문) 값 else 값

//when
when(변수){
    ->
}

when(){
    in
    !in
    else
}

when(){
    is
}

//for
for(item: Int in ints){

}

```

### class

```kotlin
class 클래스이름{
    //프로퍼티
    //var는 get, set 메서드가 자동으로 만들어진다, 메서드 호출은 그냥 프로퍼티 이름으로 한다
    //val는 get만 만들어진다

    //메서드

}
```
- data obejct
  - 클래스 선언시 data 키워드 사용
    - equals(), toString(), hashCode(), copy(), componentN() 메서드 자동 생성

- value object
  - kotlin1.5 부터 추가된 기능
  - 사용자 정의 메서드 없이 프로퍼티만 있는 클래스
  - 클래스 선언시 value 키워드 사용
    - equals(), toString(), hashCode() 메서드 자동 생성

- companion object
  - kotilin엔 static 키워드가 없다.
  - 정적 변수를 선언하고 싶을땐 companion object 키워드 사용

```kotlin
class 클래스이름{
    companion object{
        //정적(static) 변수 선언
    }
}
```

- 상속
  - open 키워드

- 싱글톤(single tone)
  - object 키워드를 클래스 정의시 붙이면 컴파일시 싱글톤 객체를 만든다.


### scope function
- let
  - 널이 아닌 객체의 코드 블록
  - 로컬 범위에서 변수로 표현식 실행
- apply
  - 객체 초기화
- run
  - 객체 초기화와 결과값 계산
  - 표현식이 필요한 실행문
- also
  - 부수적 효과
- with
  - 객체의 메소드 호출을 그룹핑

### 고차함수

### 람다

### 리시버

### 확장함수

### 코루틴(Coroutine)
- kotlin thread 라이브러리
- 비동기(asynchronous) 처리 구현에 사용된다 

- builder
  - runBlocking
  - launch
  - async 

