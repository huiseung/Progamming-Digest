# 목차
- [목차](#목차)
- [안드로이드](#안드로이드)
  - [프로젝트 구조](#프로젝트-구조)
  - [시작 코드](#시작-코드)
  - [성능 모니터링](#성능-모니터링)
  - [로그](#로그)
- [Design Pattern](#design-pattern)
  - [MVVM](#mvvm)
- [화면구성과 생명주기](#화면구성과-생명주기)
  - [레이아웃](#레이아웃)
  - [위젯](#위젯)
  - [화면구성](#화면구성)
    - [액티비티](#액티비티)
    - [컨테이너](#컨테이너)
    - [프래그먼트](#프래그먼트)
    - [뷰](#뷰)
    - [탭](#탭)
- [API 통신](#api-통신)
- [데이터 저장](#데이터-저장)
  - [파일 입출력](#파일-입출력)
  - [SharedPreferences](#sharedpreferences)
  - [SQLite](#sqlite)
- [카메라](#카메라)
- [코루틴](#코루틴)
- [컨텐츠리졸버](#컨텐츠리졸버)
- [구글지도](#구글지도)

# 안드로이드 
- 리눅스 커널 + 안드로이드런타임(ART) + Java API Framework

- 소스 코드 -(빌드)-> 앱 설치 파일(APK파일) -(업로드)-> 앱스토어 -(다운로드)-> 스마트폰 -(실행)-> 앱

- 젯팻(wptvor)


## 프로젝트 구조
```
|-app
  |-manifests
  |-java
  |-res
  |-Gradle Scripts
    |-build.gradle(Project)
    |-build.gradle(Module)
```

- 페키지 이름에 example이 들어가면 앱스토어 등록 불가

## 시작 코드

- MainActivity.kt
```kotlin
class MainActivity:AppCompatActivity(){
  overrride fun onCreate(savedInstanceState: Bundle?){
    supser.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
```

- AndroidManifest.xml
```kotlin


```

## 성능 모니터링
- Profiler 탭에서 실생중인 앱에 CPU, 메모리 사용량 모니터링 가능

## 로그
- logcat 탭에서 로그 확인 가능, 태그 기반으로 로그 검색 가능
- android.util.Log

|함수|의미|내용|
|---|---|---|
|Log.v(태그, 내용)|verbose|상세 내용|
|Log.d(태그, 내용)|debug|개발시 필요 사항|
|Log.i(태그, 내용)|information|정보 제공용 메세지|
|Log.w(태그, 내용)|warning|경고성 메세지|
|Log.e(태그, 내용)|error|오류 메세지

# Design Pattern
## MVVM
- Model-View-ViewModel
  - Model
  - View
  - ViewModel
- 데이터 바인딩

```build.gradle(Project)
repositories{
    maven{url 'https://jitpack.io' }
}

```

# 화면구성과 생명주기
## 레이아웃

## 위젯

## 화면구성
### 액티비티
- 컨텍스트
- 인텐트

### 컨테이너
- 스피너
- 리사이클러뷰

### 프래그먼트

### 뷰

### 탭


# API 통신

# 데이터 저장
## 파일 입출력

## SharedPreferences

## SQLite

# 카메라

# 코루틴

# 컨텐츠리졸버

# 구글지도