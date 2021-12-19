# 목차
- [목차](#목차)
- [안드로이드](#안드로이드)
  - [프로젝트 구조](#프로젝트-구조)
  - [성능 모니터링](#성능-모니터링)
  - [로그](#로그)
  - [라이브러리](#라이브러리)
- [Design Pattern](#design-pattern)
  - [MVVM](#mvvm)
  - [코드 작성양을 줄이는 라이브러리](#코드-작성양을-줄이는-라이브러리)
    - [Anko](#anko)
    - [splitties-toast](#splitties-toast)
- [화면 구성](#화면-구성)
  - [Design](#design)
    - [Layout](#layout)
      - [Constraint Layout](#constraint-layout)
      - [LinearLayout](#linearlayout)
      - [FrameLayout](#framelayout)
    - [Text](#text)
    - [Buttons](#buttons)
      - [ImageButton](#imagebutton)
      - [RadioButton](#radiobutton)
      - [CheckBox](#checkbox)
      - [ToggleButton](#togglebutton)
      - [Switch](#switch)
    - [Widgets](#widgets)
      - [ProgressBar](#progressbar)
      - [SeekBar](#seekbar)
      - [RatingBar](#ratingbar)
      - [VideoView](#videoview)
      - [CalendarView](#calendarview)
      - [SearchView](#searchview)
    - [Containers](#containers)
      - [ScrollView](#scrollview)
      - [RecyclerView](#recyclerview)
  - [drawable 과 mipmap](#drawable-과-mipmap)
  - [Context](#context)
  - [Intent](#intent)
  - [Activity](#activity)
    - [Activity LifeCycle](#activity-lifecycle)
- [데이터 저장](#데이터-저장)
  - [파일 입출력](#파일-입출력)
  - [SharedPreferences](#sharedpreferences)
    - [객체를 생성하는 방법](#객체를-생성하는-방법)
    - [Editor 클래스](#editor-클래스)
  - [SQLite](#sqlite)
- [API 통신](#api-통신)
- [카메라](#카메라)
- [코루틴](#코루틴)
- [컨텐츠리졸버](#컨텐츠리졸버)
- [구글지도](#구글지도)

# 안드로이드 
- 리눅스 커널 + 안드로이드런타임(ART) + Java API Framework

- 소스 코드 -(빌드)-> 앱 설치 파일(APK파일) -(업로드)-> 앱스토어 -(다운로드)-> 스마트폰 -(실행)-> 앱

- 젯팻 Jetpack


## 프로젝트 구조
- 안드로이드뷰: 개발 편의성을 위해 안드로이드 스튜디오가 만들어낸 가상에 폴더 구조
```
|-app
  |-manifests
  |-java
  |-res
    |-layout
  |-Gradle Scripts
    |-build.gradle(Project)
    |-build.gradle(Module)
```

- 페키지 이름에 example이 들어가면 앱스토어 등록 불가


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

## 라이브러리

```
  //basic
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    //API
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_Version"
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    //lifecycle

    //recyclerview

    //

```


# Design Pattern
## MVVM
- Model: API 호출, local DB 데이터 처리
- ViewModel: 데이터 관리
- View: 데이터 보여주기, 행동 받기, Model과 의존관계가 없으며 ViewModel만을 observe
- obseve
  - DataBinding
  - LiveData
  - Koin

## 코드 작성양을 줄이는 라이브러리
- 버전지원이 끝나는 경우가 생길 수 있으니 가급적 사용하지 않기, 다른 사람 코드 읽기 용으로 공부
### Anko
  
### splitties-toast
- splitties-activities
```kotlin
//기본
val intent = Intent(this, SomActivity::class.java)
startActivity(intent)

//라이브러리 사용시
start<SomeActivity>
```

- splitties-toast
```kotlin
//기본
Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show()

//라이브러리 사용시
toast("Message")

```

------

# 화면 구성
## Design
- res/layout에 위치하는 xml 파일이용
- 안드로이드 스튜디오는 코드를 통해서가 아니라 상호작용형태로 레이아웃 디자인을 꾸밀 수 있다
- 화면을 가로로 전환해도 그대로인지 살펴봄으로서 제대로 레이아웃 배치가 되었는지 확인해 볼 수 있다.

### Layout
#### Constraint Layout
- 위젯 사이 상대적인 제약 조건을 이용해 화면을 구성하는 기본 레이아웃
- Constraint: 
- Anchor Point
- Size Handler
  - wrap_content
  - Fixed
  - Match Constraintt
    - Aspect Ratio
- Bias
- Chaining

- ex: 복수의 위젯들에 상대적 위치 설정 방법
  
![ex1](./image/layout_widget/ConstrainLayout1.PNG)


#### LinearLayout
- 가로, 세로 한줄로 위젯을 배치하는 레이아웃
- orientation: horizontal or vertical
- gravity
- space

#### FrameLayout
- 위젯 중첩 레이아웃
 
### Text
- 밑줄이 처진 Ab 아이콘이 있는 위젯은 텍스트 내용을 편집 가능한 위젯이다.
- res/values/strings.xml에 텍스트위젯에 들어갈 내용을 관리한다
- Attributes/text 속성에 값으로 @string/name을 입력해 strings.xml에서 설정한 string name에 해당하는 값을 텍스트위젯에 할당한다.
- 텍스트 크기는 주로 sp 단위를 사용한다. res/values/dimens.xml 파일을 생성해 관리한다

- viewBinding
  - build.gradle(Module) 설정
```
android{
  buildFeatures{
    viewBinding = ture
  }
}

```

  - MainActivity.kt에 프로퍼티 추가
```kotlin
class MainActivity: AppCompatActivity(){
  val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.id
  }

}

```

### Buttons
- 버튼 위젯들은 클릭 처리 위젯이다.
  - 터치: 특정 위치를 건드릴시 이벤트 발생
  - 클릭: 특정 위치를 건드렸다가 손가락을 땔때 이벤트 발생

#### ImageButton
#### RadioButton
#### CheckBox
#### ToggleButton
#### Switch

### Widgets
#### ProgressBar
#### SeekBar
#### RatingBar
#### VideoView
#### CalendarView
#### SearchView

### Containers
#### ScrollView
- 스크롤 기능을 사용할 수 있는 View, 위에 LinearLayout를 올려 사용한다
- root element를 Scrollview로 설정하면 사용할 수 있다

#### RecyclerView


## drawable 과 mipmap
- 스마트폰마다 해상도가 달라 이미지 파일의 경우 해상도에 맞춰 res/drawable/drawable-* 형태의 폴더에 넣어두어야 한다
  - drawable-mdpi : 1인치안에 160 화소
  - drawable-hdpi : 1인치안에 240 화소
  - drawable-xhdpi : 1인치안에 320 화소
  - drawable-xxhdpi : 1인치안에 480 화소
  - drawable-xxxhdpi : 1인치안에 640 화소

- 아이콘 이미지에 경우도 일반 이미지처럼 화소 단위로 분류해 mipmap 폴더에 넣어둔다.


## Context
- Application Context
  - 싱글톤
- Base Context
  - Activity, Service, Content Provider, Broadcast Receiver의 부모 클래스
## Intent
- bundle
  - 인텐트 내부에 있는 데이터 저장 공간, 액티비티들은 번들을 활용해 데이터를 주고 받는다 

## Activity
- 사용자 혹은 다른 앱과 상호작용하는 진입점
- 사용자 혹은 다른 앱이 앱을 호출하는 건 사실 하나에 Activity를 호출하는 것이다.
- Base Context를 상속받은 클래스, 컴포넌트 중 하나

### Activity LifeCycle

|메서드|상태|
|---|---|
|onCreate()|액티비티 생성|
|onStart()|화면에 나타나기 시작|
|onResume()|화면에 보여지는 중|
|onPause()|다른 액티비티에 의해 일부분이 가려짐|
|onStop()|다른 액티비티가 실행되어 화면에서 사라짐|
|onDestroy()|액티비티 종료|


- 메서드 안에서 super를 호출하지 않으면 정상 작동하지 않는다


-------
# 데이터 저장
## 파일 입출력

## SharedPreferences
- 데이터 저장/불러오기 기능이 있는 클래스
- 데이터는 XML파일로 관리되지만 코드상에선 Map 클래스처럼 이용 가능하다
### 객체를 생성하는 방법
```kotlin
val sharedPref = getPreferences(int mode)
val sharedPref = getSharedPreferences(String fileName, int mode)
val sharedPref = PreferenceManager.getDefaultSharedPreferences(this) //androidx.preference:preferece-ktx:1.1.0 라이브러리 추가 필요
```
- mode
  - MODE_PRIVATE:
  - MODE_WORLD_READABLE //해당 모드는 API-Level17부터 지원 중단 상태라 사용하지 않는다.
  - MODE_WORLD_WRITEABLE //해당 모드는 API-Level17부터 지원 중단 상태라 사용하지 않는다.

### Editor 클래스
```kotlin
val editor: SharedPreferences.Editor = sharedPref.edit()

//저장
editor.puString(String key, String value)
editor.putInt(String key, int value)
editor.putLong(String key, long value)
editor.putFloat(String key, float value)
editor.putBoolean(String key, boolean value)

//반영
editor.commit()
editor.apply()

//불러오기
val data: String = editor.getString(String key, String defaultValue)
val data: Int = editor.getInt(String key, int defaultValue)
val data: Long = editor.getLong(String key, long defaultValue)
val data: Float = editor.getString(String key, float defaultValue)
val data: Boolean = editor.getBoolean(String key, Boolean defaultValue)

```




## SQLite

------

# API 통신

# 카메라

# 코루틴

# 컨텐츠리졸버

# 구글지도