# 목차
- [목차](#목차)
- [객체 지향 프로그래밍 OOP](#객체-지향-프로그래밍-oop)
- [JVM](#jvm)
- [키워드 Keyword](#키워드-keyword)
- [Primitive Type, Wrapper Class, Array](#primitive-type-wrapper-class-array)
- [java.util.Collections](#javautilcollections)
- [Class](#class)
- [Interface](#interface)
- [Anotation](#anotation)
- [Enum](#enum)
- [Exception과 Error](#exception과-error)
- [리플랙션 reflection](#리플랙션-reflection)
- [java8 신기능](#java8-신기능)

# 객체 지향 프로그래밍 OOP
- Object Oriented programming
- 역할(책임)을 갖는 객체들이 데이터를 처리하고 메세지를 주고 받는 형태로 동작하는 프로그래밍 방식
- 장점
  - 유지보수, 코드 재사용이 쉽다
- 단점
  - 절차지향형 대비 실행속도 느리다

<details>
<summary>
객체 지향 프로그래밍에 특징
</summary>
<ul>
<li>
<p>캡슐화</p>
<p>객체에 속성과 기능을 외부로 부터 숨긴다</p>
</li>
<li>
<p>추상화</p>
<p>설계(인터페이스)와 구현(클래스)이 존재한다</p>
</li>
<li>
<p>상속</p>
<p>부모 클래스에 속성과 기능을 자식 클래스가 재사용</p>
</li>
<li>
<p>다형성</p>
<p>오버로딩 overload</br> 한 클래스에서 이름이 같고 파라미터가 다른 기능들을 정의해 사용(같은 파라미터 다른 객체 리턴 형식에 오버로딩은 불가)</p>
<p>오버라이딩 override</br> 부모 클래스에 기능을 자식 클래스가 재정의해 사용</p>
</li>
</ul>
</details>

</br>
<details>
<summary>
객체 지향 프로그래밍 SOLID 5대 원칙
</summary>
<ul>
<li>
<p>single responsibility principle</p>
<p>하나에 객체는 하나에 책임만 갖게 설계한다</p>
</li>
<li>
<p>open close principle</p>
<p>객체에 기능을 추가할때, 기존 코드 수정은 일어나지 않게 설계한다.</p>
</li>
<li>
<p>liskov subtitution principle</p>
<p>자식 클래스는 부모 클래스에 기능을 수행해야한다</p>
</li>
<li>
<p>interface segregation principle</p>
<p>하나에 큰 설계(인터페이스)보다 여러개로 쪼갠 설계한다</p>
</li>
<li>
<p>dependecy inversion principle</p>
<p>객체가 속성으로 사용하는 객체(의존 객체)는 인터페이스로 정의해 두고, 어떤 구현체를 사용할지를 외부에서 지정한다</p>
</li>
</ul>
</details>

-------------------------

# JVM
- java virtual machine
- java파일을 실행시키는 소프트웨어
- java 실행파일은 빌드시 사용한 os에 무관하게 사용하는 os가 어디든 실행 가능하다


<details>
<summary>java 코드 실행 과정</summary>
<p>JIT(Just In Time)이 컴파일러와 인터프리터 두 가지 방식을 채용한다<p>
<ol>
<li>.java 파일을 javac 컴파일러가 .class 파일(Byte Code file)로 바꿉니다.</li>
<li>인터프리터가 .class 파일을 OS에 맞춘 기계어로 번역해 OS가 명령을 실행시킵니다.</li>
</ol>
</details>

</br>
<details>
<summary>컴파일러와 인터프리터 차이</summary>
<ul>
<li>
<p>컴파일러</p>
<p>소스코드 전체를 수집 재구성해 기계어로 변환</p>
</li>
<li>
<p>인터프리터</p>
<p>소스코드를 줄 단위로 분석해 실행</p>
</li>
</ul>
</details>


</br>

![](./image/jvm.PNG)

<details>
<summary>JVM에 구조</summary>
<ul>
<li>
<p>class loader</p>
<p>class 파일들을 runtime data area에 적재</p>
</li>

<li>
<p>runtime data area</p>
<p>OS로부터 할당받은 프로세스가 사용할 수 있는 메모리 공간</p>
<ul>
<li>
<p>method area(static area)</p>
<p>runtime 종료시까지 저장 정보 유지.</br>
static 변수, 클래스 정보(이름, 속성타입, 접근제어자), interface 정보 저장
</p>
</li>
<li>
<p>heap area</p>
<p>실제 인스턴스 값이 저장, 가비지 컬렉터가 관리한다</p>
</li>
<li>
<p>stack area</p>
<p>메서드 호출시 지역변수 저장, 리턴시 해제된다</p>
</li>
<li>
<p>pc register</p>
<p>다음 실행시킬 명령어가 있는 메모리 주소 저장</p>
</li>
<li>
<p>native method stack</p>
<p>자바외에 언어로 작성된 코드 저장</p>
</li>
</ul>
</li>

<li>
<p>Excution Engine</p>
<p></p>

<ul>
<li>
<p>가비지 컬렉터 Garbage Collactor</p>
<p>heap area를 관리하는 소프트웨어</p>
</li>
</ul>
</li>

</ul>
</details>

</br>
</details>
<summary>Garbage Collactor 동작 방식</summary>
<p>heap area는 Eden, Survivor0, Survivor1, Old 영역으로 구분된다</p>
<p>1. 처음 생성된 인스턴스는 Eden에 저장</p>
<p>2. Minor GC</br>Eden이 가득 차면 Mark and Sweep 과정을 거처 살아남은 정보들을 Survivor0로 옮긴다</p>
<p>Eden에서 Survivor0로 옮길때 Survivor0가 가득차면, Mark and Sweep 과정을 거처 살아남은 정보들을 Survivor1으로 옮겨 Survivor0를 비운다(항상 survivor0과 1중 한쪽에만 데이터를 저장하는 상태를 유지한다)</br> 옮길때 정보들에 나이가 1 증가한다</p>
<p>survivor0과 survivor1을 옮겨다니며 나이가 특정 수치가 되면 old로 옮겨진다</p>
<p>3. Major GC</br>old가 가득차면 mark and swap과정을 거처 미사용객체를 지운다.</br>해당 동작 중엔 프로그램 run이 멈춘다</p>
</details>

---------------

</br>
<details>
<summary>java version update 중요사항</summary>
<ul>
<li>
<p>5</p>
<p>genric 추가</p>
</li>

<li>
<p>8</p>
<p>lambda, stream, optional, interface에 default method 추가</p>
</li>

<li>
<p>11</p>
<p></p>
</li>
</ul>
</details>
</br>

------------------

# 키워드 Keyword

<details>
<summary>접근 제한자를 설명해주세요</summary>
<ul>
<li>
<p>public</p>
<p>어디든 접근 가능</p>
</li>
<li>
<p>default</p>
<p>접근 제어자를 쓰지 않으면 default 취급, 같은 패키지에 클래스들에서 접근 가능</p>
</li>
<li>
<p>protected</p>
<p>같은 패키지에 클래스들 혹은 다른 패키지에 자식 클래스에서 접근 가능</p>
</li>
<li>
<p>private</p>
<p>자기 클래스에서만 접근 가능</p>
</li>
<li>
</li>
</ul>
</details>
</br>

<details>
<summary>static 설명해주세요</summary>
</details>
</br>

<details>
<summary>final 설명해주세요</summary>
</details>
</br>

# Primitive Type, Wrapper Class, Array


<details>
<summary>
String, StringBuilder, StringBuffer 차이 설명
</summary>
<ul>
<li>
<p>String</p>
<p>immutable object, thread safe</p></li>
<li>
<p>StringBuilder</p>
<p>mutable object, no thread safe</p></li>
<li>
<p>StringBuffer</p>
<p>mutable object, thread safe</p></li>
</ul>
</details>
</br>

<details>
<summary>
불변객체 immutable object
</summary>
<p>값을 변경시, 새로 인스턴스를 생성하는 객체</p>
</details>
</br>

<details>
<summary>
String(literal ""), String(new String("")) 차이 설명
</summary>

```java
String str = "test"; //literal
String str = new String("test");
```
<ul>
<li>
<p>literal</p>
<p>intern pool에 저장되어, 같은 문자열을 다른 변수에 추가로 선언할때 같은 intern pool주소를 가르키게 설정된단</p>
</li>
<li>
<p>new String</p>
<p>새 변수 선언시마다 새로 인스턴스를 할당한다</p>
</li>
</ul>

</details>
</br>

-----------------------------------


# java.util.Collections


-----------------------------------

# Class
- 구현체
- 속성(atrribute), 기능(method), 생성자(constructor)로 이루어져있다.

<details>
<summary>
constructor design pattern: singletone, builder, static factory method 
</summary>

<ul>
<li>
<p>singletone
</p>
<p>runtime 동안 오직 하나에 인스턴스만 생성되는 객체
</p>
</li>
<li><p>builder</p>
<p>생성자에 파라미터 설정 가독성이 높은 패턴, 파라미터가 많을때 이용</p>
</li>
<li><p>static factory method</p>
<p>객체 생성 메서드, public static 객체 of(파라미터) 형태로 주로 사용해 객체 생성시 new가 아니라 생성자에 이름을 갖게 할 수 있다</p>
</li>
</ul>
</details>
</br>

<details>
<summary>
call by value 와 call by reference
</summary>
</details>
</br>

# Interface
- 클래스에 설계도
- 사용 목적
  - 여러 사람이 코드를 작성할때 메서드 이름, 변수, 반환값을 사전에 약속 할 수 있다
  - 동일 설계에 여러 구현체를 만들 수 있다.
  - 객체를 속성으로 갖을때, 클래스가 아니라 인터페이스로 정의해 두고 의존성 주입을 하면, 속성 구현체 변경이 용이하다
- 하나에 클래스가 여러 인스턴스를 구현화 할 수 있다

```java
interface MyInstafaceA{

}

class MyClass implements MyInstanceA, MyInstanceB{

}
```

<details>
<summary>
inteface와 abstract class 차이
</summary>

</details>
</br>

# Anotation
인터페이스를 기반으로 한 문법으로 주석처럼 코드에 달아 클래스에 특별한 의미를 부여하거나 기능을 주입할 수 있습니다. 

# Enum

---------------

# Exception과 Error

<details>
<summary>
java Expcetion, Error 상속 관계
</summary>

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
</details>
</br>

<details>
<summary>
CheckedException 과 UncheckedException
</summary>
<ul>
<li><p>CheckedException</p>
<p>compile시 확인 가능한 오류, 예외처리를 하지 않으면 컴파일 불가</p>
<p>Exception에 자식 클래스중 RuntimeException을 제외한 모든 클래스</p>
</li>
<li><p>UncheckedException</p>
<p>compile시 잡히지 않고 실행중 발견되는 오류 개발자 스스로 알아채 예외처리 코드를 짜둬야한다 </p>
<p>RuntimeException과 Error</p>
</li>
</ul>
</details>
</br>

---------------------

# 리플랙션 reflection


# java8 신기능

<details>
<summary>람다 lambda</summary>
</details>
</br>

<details>
<summary>스트림 stream</summary>
</details>
</br>

<details>
<summary>Optional</summary>

```java

```

</details>
</br>