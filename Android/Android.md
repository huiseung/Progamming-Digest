# 목차

- [목차](#목차)
- [안드로이드 스튜디오](#안드로이드-스튜디오)
  - [안드로이드 버전 정보](#안드로이드-버전-정보)
  - [프로젝트](#프로젝트)
    - [프로젝트 구조와 Design Pattern](#프로젝트-구조와-design-pattern)
      - [Dependency Injection](#dependency-injection)
        - [Koin](#koin)
        - [Hilter](#hilter)
      - [MVVM](#mvvm)
    - [라이브러리 관리](#라이브러리-관리)
      - [gradle(Project)](#gradleproject)
      - [gradle(Module)](#gradlemodule)
    - [AndroidManifest.xml](#androidmanifestxml)
      - [첫 생성 파일](#첫-생성-파일)
      - [AndroidManifest에서 설정 가능한 사항들(태그 설명)](#androidmanifest에서-설정-가능한-사항들태그-설명)
        - [application 태그](#application-태그)
        - [activity 태그](#activity-태그)
    - [Image/Vector Asset 추가](#imagevector-asset-추가)
    - [res 폴더](#res-폴더)
      - [drawable](#drawable)
      - [layout](#layout)
      - [minmap](#minmap)
      - [values](#values)
      - [menu](#menu)
  - [애뮬레이터](#애뮬레이터)
    - [로그](#로그)
    - [성능 모니터링](#성능-모니터링)
- [Test Code](#test-code)
- [화면 구성](#화면-구성)
  - [Splash Screen](#splash-screen)
  - [Design Palette](#design-palette)
    - [Layout](#layout-1)
    - [Text](#text)
    - [Button](#button)
    - [Widgets](#widgets)
    - [Containers](#containers)
  - [Activity](#activity)
    - [Acitivity LifeCycle](#acitivity-lifecycle)
    - [ViewBinding](#viewbinding)
    - [Intent](#intent)
    - [Fragment](#fragment)
      - [Fragment LifeCycle](#fragment-lifecycle)
- [Data](#data)
    - [SharedPreferences](#sharedpreferences)
    - [SQLite와 Room](#sqlite와-room)
  - [API](#api)
- [코루틴 Coroutine](#코루틴-coroutine)
  - [안드로이드에서 스레드](#안드로이드에서-스레드)
  - [Coroutine overview](#coroutine-overview)
  - [Coroutine Builder](#coroutine-builder)
    - [launch()](#launch)
      - [Coroutine Dispatcher](#coroutine-dispatcher)
    - [asysnc()](#asysnc)
    - [cancle()](#cancle)
    - [join()](#join)
    - [suspend 키워드와 withContext()](#suspend-키워드와-withcontext)



# 안드로이드 스튜디오
## 안드로이드 버전 정보



## 프로젝트
### 프로젝트 구조와 Design Pattern
#### Dependency Injection
- 라이브러리
  - Dagger, Koin, Hilter

##### Koin
- module(): 
- single(): 싱글톤 객체 생성
- factory(): 요청마다 새 객체 생성
- get(): 의존성 주입

##### Hilter


#### MVVM
```
|-app
    |-data
        |-entitiy
        |-network
        |-response
        |-repository
        |-dataStore
    |-domain
        |-model
        |-UseCase
    |-presentation
        |-viewModel
        |-view
    |-di
```


### 라이브러리 관리
#### gradle(Project)

```

```

#### gradle(Module)

```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.huiseung.myapplication"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }

    buildFeatures{
        viewBinding true
    }

}

dependencies {
    //basic
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    ///
    
    //coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3"

    //API
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //koin
    implementation "org.koin:koin-android:2.1.5"
    implementation "org.koin:koin-android-scope:2.1.5"
    implementation "org.koin:koin-android-viewmodel:2.1.5"
    implementation "org.koin:koin-android-ext:2.1.5"
    testImplementation "org.koin:koin-test:2.1.5"
    
    //mokito
    testImplementation "org.mockito:mocito-inline:3.4.0"
    testImplementation "android.arch.core:core-testing:1.1.1"
    
    // lifecycle
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
}

```

### AndroidManifest.xml
#### 첫 생성 파일

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="">

    <application
        android:allowBackup="false"
        android:icon="@mipmap/"
        android:label="@string/"
        android:roundIcon="@mipmap/"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyApplication">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>

```

#### AndroidManifest에서 설정 가능한 사항들(태그 설명)
##### application 태그
  - android:icon=애플리케이션 기본 아이콘 설정
  - android:roundIcon=애플리케이션 둥근 아이콘 설정
  - android:label=애플리케이션 이름 설정
  - android:allowBackup="false"
  - android:supportsRtl="true"
  - android:theme=""

##### activity 태그
- android:name=""
- android:exported=""
- intent-filter 태그
  - 애플리케이션 시작시 첫 액티비티 결정
  - action android:name=""
  - category android:name=""



### Image/Vector Asset 추가
- File > new > Image Asset
- File > new > Vector Asset


### res 폴더
#### drawable
#### layout
#### minmap
#### values
- colors.xml
- strings.xml
- themes/themes.xml
#### menu

## 애뮬레이터
### 로그
### 성능 모니터링



-------------

# Test Code




---------------

# 화면 구성
## Splash Screen
- SplashScreen용 액티비티를 만들고 해당 액티비티에 일정시간 대기후 다른 액티비티로 이동하는 방식으로 동작
- 액티비티 전환시 corutine을 이용하면 스레드차원에서 효율적이다

```kotlin
class SplashActivity : AppCompatActivity() {
    private val binding by lazy {ActivitySplashBinding.inflate(layoutInflater)}
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activityScope.launch{
            delay(3000)
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause(){
        super.onPause()
        activityScope.cancel()
    }
}
```


```xml
    <application
        android:allowBackup="false"
        android:icon="@mipmap/shopping_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/shopping_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyApplication">
        <activity
            android:name=".ui.SplashActivity"
            android:exported="true" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".MainActivity"
            android:exported="true">
        </activity>
    </application>

```

------------

## Design Palette
### Layout
### Text
### Button
### Widgets
### Containers


-------------

## Activity
### Acitivity LifeCycle
### ViewBinding
### Intent
### Fragment
#### Fragment LifeCycle


-------------

# Data
### SharedPreferences
### SQLite와 Room

## API

# 코루틴 Coroutine
## 안드로이드에서 스레드
- 안드로이드의 스레드에는 1개에 메인 스레드와 여러개의 백그라운드 스레드가 있다
- Main Thread
  - 앱을 실행하면 안드로이드는 메인 스레드를 앱에 할당한다
  - 메인스레드는 화면을 그리는 처리를 담당한다
- Background Thread
  - 메모레 외 공간에서 데이터를 가져오는 작업(네트워크, 파일 업로드/다운로드, 이미지 처리)은 백그라운드 스레드를 할당해 처리
- Looper
  - 메인스레드는 루퍼를 갖고 있다
  - Message Queue 자료구조를 갖는다
  - Message Queue에는 다른 스레드나 자신으로부터 전달 받은 메세지를 보관한다
  - 루퍼는 메세지큐에서 메세지를 꺼내 핸들러가 처리하게 전달한다
- Handler
  - 루퍼로 부터 받은 메세지를 처리
  - 루퍼에 메세지를 넣는 다른 스레드와 메인 스레드간 통신 장치

## Coroutine overview
- task 단위 = Coroutine이란 Object
- no thread context switching: 하나의 main thread에 복수에 coroutine 존재 가능
- asynchronus

## Coroutine Builder
### launch()
- GlobalScope.launch{}
  - 앱의 생명주기와 같이 코루틴 활용
- CoroutineScope(Dispatchers.IO).launch{}
  - 특정 시점에만 코루틴 활용
- Job 객체를 반환

#### Coroutine Dispatcher
- CoroutineScope에 인자로 넣어 코루틴을 정의하는 값

|종류|역할|
|---|---|
|Dispathcers.IO|파일 입출력에 최적화된 디스패처|
|Dispatchers.Main|UI 상호작용에 최적화된 디스패치|
|Dispatchers.Default||
|Dispatchers.Unconfined||

### asysnc()

### cancle()

### join()

### suspend 키워드와 withContext()
- 코루틴안에서 suspend 함수를 실행하면 해당 함수가 처리될때 까지 이후 동작들을 비동기적으로 처리하지 않는다  
- withContext는 suspend 함수를 실행할때 디스패처를 바꾸고 싶을때 사용
  
```kotlin

suspend fun readFile(): String{

}


CoroutineScope(Dispatchers.Main).launch{
    val result = withContext(Dispatchers.IO){
        readFile()
    }
}
```





---------------