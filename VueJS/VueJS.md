# 목차
- [목차](#목차)
- [프론트엔트가 알아야할 사항들](#프론트엔트가-알아야할-사항들)
- [역사](#역사)
- [nvm과 npm](#nvm과-npm)
  - [nvm](#nvm)
- [프로젝트](#프로젝트)
  - [프로젝트 생성](#프로젝트-생성)
    - [ESLint](#eslint)
    - [jsconfig.json](#jsconfigjson)
    - [.env](#env)
    - [Snippets: vscode 자주 사용하는 코드 패턴 등록](#snippets-vscode-자주-사용하는-코드-패턴-등록)
  - [프로젝트 구조](#프로젝트-구조)
    - [package.json](#packagejson)
  - [webpack](#webpack)
- [routing](#routing)
  - [Lazy Load](#lazy-load)
- [Component](#component)
  - [Life Cycle](#life-cycle)
    - [created](#created)
    - [mounted](#mounted)
    - [updated](#updated)
    - [unmounted](#unmounted)
  - [Data Binding](#data-binding)
  - [Component 삽입, Component간 data 주고받기](#component-삽입-component간-data-주고받기)
    - [provide/inject](#provideinject)
  - [slot](#slot)
- [API](#api)
  - [axios와 mixins](#axios와-mixins)
- [WebStorage: sessionStorage와 localStorage](#webstorage-sessionstorage와-localstorage)
  - [xss 취약점](#xss-취약점)
    - [대응책](#대응책)
- [sessionCookie와 persistentCookie](#sessioncookie와-persistentcookie)
  - [csrf 취약점](#csrf-취약점)
    - [대응책](#대응책-1)
- [vuex](#vuex)
  - [vuex-persistedstate](#vuex-persistedstate)
  - [State](#state)
  - [Mutations](#mutations)
  - [Actions](#actions)
- [accessToken과 RefreshToken 관리](#accesstoken과-refreshtoken-관리)

# 프론트엔트가 알아야할 사항들 
- view: 웹디자인을 구현하는 법
  - event listenr: 이벤트 처리하는 법
- routing: 웹페이지간 이동하는 법
- api call: 서버와 통신하는 법
- store: 페이지끼리 공유해 사용하는 데이터를 저장 관리하는 법
- test: 테스트를 하는 법
- distribute: 배포하는 법
  
# 역사
- SPA 개발을 위한 자바스크립트 프레임워크
- AngularJS를 사용하던 Evan You가 2014년에 공개했다
- AngularJS의 two way DataBinding과 ReactJS의 VDOM 개념을 이용할 수 있다

# nvm과 npm
## nvm
```bash

```
- npm install -g 패키지명
- npm install 패키지명 --save
- npm audit fix
- npm run serve

# 프로젝트 
- CLI(Command Line Interface) 설치: npm install -g @vue/cli
- 라이브러리 설치: npm install 라이브러리명
- 웹서버 실행: npm run serve

## 프로젝트 생성
- vue create 프로젝트명
  - Manually select features
  - Babel, Linter, Unit
  - ver2
  - ESLint+Prettier
  - Lint on Save
  - Jest
  - In dedicated config files
  - vue2-basic으로 설정을 저장해 다음부턴 이 설정을 이용
 
### ESLint
- ESLint: JS에서 발생할 수 있는 문법 오류 검사 도구
- .eslintrc.js 수정
  - rules 값을 다음 처럼 수정

```js
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
    "prettier/prettier":[
      'error',
      {
        endOfLine: 'auto',
        singleQuote: false,
        semi: false,
        useTabs: true,
        tabWidth: 2,
        trailingComma: 'all',
        printWidth: 80,
        bracketSpacing: true,
        arrowParens: 'avoid',
      }
    ],
  },
```

### jsconfig.json
- vscode 기능, jsconfig.js 파일을 만든다
- 상대경로가 아닌 별칭을 활용한 절대 경로로 import 할 수 있게 설정
- import 시 경로명을 @로 시작하면 src에서 경로 탐색 시작 가능

```js
{
  "compilerOptions": {
    "baseUrl": ".",
    "paths": {
      "~/*": [
        "./*"
      ],
      "@/*": [
        "./src/*"
      ],
    }
  },
  "exclude": [
    "node_modules",
    "dist"
  ]
}

```

### .env
- .env.development, .envproduction 파일을 만든다
- vue-cli3부터 .env 파일안에 VUE_APP_ 으로 시작하는 환경 변수는 자동 로드 가능하다
- VUE_APP_API_URL = http://localhost:8000

### Snippets: vscode 자주 사용하는 코드 패턴 등록
- file > preference > User Snippets > vue.json 
- vue 파일을 만들때 vue-start란 키워드로 코드 패턴 붙여넣기 가능
```json
{
	// Place your snippets for vue here. Each snippet is defined under a snippet name and has a prefix, body and 
	// description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
	// $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the 
	// same ids are connected.
	// Example:
	// "Print to console": {
	// 	"prefix": "log",
	// 	"body": [
	// 		"console.log('$1');",
	// 		"$2"
	// 	],
	// 	"description": "Log output to console"
	// }

	"Generate Basic Vue Code": {
		"prefix": "vue-start",
		"body": [
			"<template>",
			"\t<div></div>",
			"</template>",
			"<script>",
			"",
			"export default {",
			"\tname:'',",
			"\tcomponents: {},",
			"\tdata() {\n\t\treturn {\n\t\t\tsampleData: ''\n\t\t}\n\t},",
			"",
			"\tbeforeCreate() {},",
			"\tcreated() {},",
			"",
			"\tbeforeMount() {},",
			"\tmounted() {},",
			"",
			"\tbeforeUpdate() {},",
			"\tupdated() {},",
			"",
			"\tbeforeUnmount() {},",
			"\tunmounted() {},",
			"",
			"\tcomputed: {},",
			"\twatch: {},",
			"\tmethods: {}",
			"}",
			"</script>",
			"<style></style>"
		]
	}
}
```


## 프로젝트 구조
```
node_modules
public
src
  |- api
    |-index.js
  |- assets
  |- components
    |-common
      |-AppHeader.vue
  |- css
  |- routes
    |-index.js
  |- store
    |-index.js
  |- utils
  |- views
    |-Home.vue
  |- App.vue
  |- main.js
tests
.browserlistrc
.env.development
.env.production
.eslintrc.js
.gitignore
babel.config.js
jest.config.js
jsconfig.json
package-lock.json
package.json
readm.md
```

- api/index.js 시작

```js
import axios from "axios"

function createInstance() {
	const instance = axios.create({
		baseURL: process.env.VUE_APP_API_URL,
	})
	return instance
}
const instance = createInstance()
```

- AppHeader.vue 시작
```vue
<template>
	<header>
		<div>
			<router-link to="/" class="logo">Home</router-link>
		</div>
		<div class="navigations"></div>
	</header>
</template>
<script>
export default {
	name: "AppHeader",
	components: {},
	data() {
		return {
			sampleData: "",
		}
	},
	beforeCreate() {},
	created() {},
	beforeMount() {},
	mounted() {},
	beforeUpdate() {},
	updated() {},
	beforeUnmount() {},
	unmounted() {},
	computed: {},
	watch: {},
	methods: {},
}
</script>
<style></style>

```

- routes/index.js 시작

```js
import Vue from "vue"
import VueRouter from "vue-router"

Vue.use(VueRouter)
export default new VueRouter({
	mode: "history",
	routes: [
		{
			path: "/",
      name: "Home",
			component: () => import("@/views/Home.vue"),
		},
	],
})

```

- store/index.js 시작

```js
import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
	state: {},
	getter: {},
	mutations: {},
})


```

- views/Home.vue 시작

```vue
<template>
	<div>
		<h1>Home</h1>
	</div>
</template>
<script>
export default {
	name: "Home",
	components: {},
	data() {
		return {
			sampleData: "",
		}
	},
	beforeCreate() {},
	created() {},
	beforeMount() {},
	mounted() {},
	beforeUpdate() {},
	updated() {},
	beforeUnmount() {},
	unmounted() {},
	computed: {},
	watch: {},
	methods: {},
}
</script>
<style></style>

```

- App.vue 시작

```vue
<template>
	<div>
		<AppHeader></AppHeader>
		<router-view></router-view>
	</div>
</template>
<script>
import AppHeader from "@/components/common/AppHeader.vue"
export default {
	name: "App",
	components: {
		AppHeader,
	},
	data() {
		return {
			sampleData: "",
		}
	},
	beforeCreate() {},
	created() {},
	beforeMount() {},
	mounted() {},
	beforeUpdate() {},
	updated() {},
	beforeUnmount() {},
	unmounted() {},
	computed: {},
	watch: {},
	methods: {},
}
</script>
<style></style>


```


- main.js 시작

```js
import Vue from "vue"
import App from "./App.vue"
import router from "@/routes/index"
import store from "@/store/index"

Vue.config.productionTip = false

new Vue({
	render: h => h(App),
	router,
	store,
}).$mount("#app")

```

- public/index.html 시작

```html
<!DOCTYPE html>
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="icon" href="<%= BASE_URL %>favicon.ico">
    <title><%= htmlWebpackPlugin.options.title %></title>
  </head>
  <body>
    <noscript>
      <strong>We're sorry but <%= htmlWebpackPlugin.options.title %> doesn't work properly without JavaScript enabled. Please enable it to continue.</strong>
    </noscript>
    <div id="app"></div>
    <!-- built files will be auto injected -->
  </body>
</html>

```



### package.json
- dependencies: npm run build시 포함되는 라이브러리 관리
- devDepencies: npm run build시 포함되지 않는 라이브러리 관리
- 자주 사용하는 라이브러리
  - router: npm install vue-router
  - axios: npm install axios
  - vuex: npm install vuex

## webpack




# routing
- npm install vue-router

## Lazy Load
- vueCLI를 이용해 build시 소스코드가 하나로 합쳐진다
  - 처음 방문시 다운 받는 자원 양이 많아 첫 렌더링이 오래걸린다는 단점이 있다
  - 한번 다운로드 받고나면 페이지 전환이 빠른 장점이 있다
- vue3는 prefecth(default: true)기능을 사용해 나중에 사용될 가능성이 있는 자원을 request로 받아 캐시에 저장해 둔다
- component(): ()=> import() 
  - path 접근 전 까지 import가 발생하지 않음

# Component
- 기본 구조
  - vscode를 사용할 경우 File>Preference>User Snippets>vue.json에 등록해 코드 탬플릿을 사용하자
- template
- script
  - import
  - export default
- name
- components
- data
  - 하나에 객체를 반환한다
  - 해당 컴포넌트가 갖고 있는 속성과 초기값을 정의
- hooks
  - life cycle에 맞춰 실행되는 메소드들
- computed
  - computed에 정의한 함수는 return이 반드시 존재해야 한다
  - computed에 정의한 함수는 parameter를 받을 수 없다. 
    - 함수 정의시엔 ()이 붙지만 template에서 호출시엔 () 를 적지 말아야 한다.
  - updated life cycle일때 computed에 정의한 함수 안에서 사용하는 변수(속성)가 변한경우에만 함수가 호출된다(이벤트 리스너 역활)
    - this.속성 방식으로 접근 
  - computed에 정의한 함수에 결과물은 캐싱된다
    - updated life cyle일때 추적하고 있는 속성이 변한게 아니라면 캐싱된 결과를 반환한다
- watch
- methods
  - methods에 정의한 함수는 parameter를 받을 수 있다
  - updated life cycle때 정의한 모든 함수들이 실행된다

## Life Cycle
### created
- beforeCreate()
- created()
### mounted
- beforeMount()
- mounted()

### updated
- beforeUpdate()
- updated()

### unmounted
- beforeUnmount()
- unmounted()


## Data Binding

- vue는 js의 data를 html에 binding 시키고, html에 입력을 js의 data에 binding 시키는 양방향 data binding 기능을 제공한다

```vue
<template>
    <div>{{string}}</div>
    <div v-html="string"></div>
    <div>
        <input type="text" v-model="valueModel"/>
        
        <input type="number" v-model.number="numberModel"/>
        
        <label><input type="checkbox" v-model="checked" true-value="yes" false-value="no">{{checked}}/></label>

        <label><input type="checkbox" v-model="checkList" value="선택1">선택1</label>
        <label><input type="checkbox" v-model="checkList" value="선택2">선택2</label>
        <label><input type="checkbox" v-model="checkList" value="선택3">선택3</label>
        <span>{{checkList}}</span>

        <label><input type="radio" v-model="picked" value="선택1"/>선택1</label>
        <label><input type="radio" v-model="picked" value="선택2"/>선택2</label>
        <label><input type="radio" v-model="picked" value="선택3"/>선택3</label>
        <span>{{picked}}</span>

        <textarea v-model="message"></textarea>
        
        <select v-model="selectModel">
            <option value="1">선택1</option>
            <option value="2">선택2</option>
        </select>
        
        <img v-bind:src="imgSrc"/>

        <input type="text" v-model="textValue"/>
        <button type="button" v-bind:disabled="textValue==''">Click</button>


    </div>
</template>

<script>
    export default{
        data() {
            return {
                string: '<p style="color:red;">This is red</p>',
                valueModel: "default text",
                numberModel: 0,
                checked: true,
                checkList: [],
                picked: '',
                message: "long default text",
                selectModel: "1",
                imgSrc: "",
                textValue: "",
            } 
        }

    }
</script>

```

```vue
<template>
    <table>
        <thread>
            <tr>
                <th>col1<th>
                <th>col2</th>
            </tr>
        </thead>
        <tbody>
            <tr :key="i" v-for="(row, i) in aList">
                <td>{{row.col1}}</td>
                <td>{{row.col2}}</td>
            </tr> 
        </tboby>
    </table>
</template>

<script>
    export default{
        data() {
            return {
                aList: {
                    {"col1":"", "col2": ""},
                    {"col1":"", "col2": ""}
                }
            }
        }
    }
</script>
```

```js
<template>
    <span v-if="condition=='a'"></span>
    <span v-else-if="condition=='b'"></span>
    <span v-else></span>
</template>
<script>
    export default{
        data(){
            return{
                condition: 'a'
            }
        }
    }
</script>
```

```vue
<template>
    <button type="button" v-on:click="increaseCounter">{{counter}}</button>
    <button type="button" v-on:click="one, two"></button>
</template>
<script>
export default{
    data(){
        return{
            counter: 0
        }
    },
    methods:{
        increaseCounter(){
            this.counter += 1
        },
        one(){

        },
        two(){

        }
    }
}
</script>
```

## Component 삽입, Component간 data 주고받기
```vue
<template>
    <h2>{{title}}</h2>
</template>
<script>
    export default {
        props: {
            title: {
                type: String,
                default: ""
            }
        }
    }
</script>
```

```vue


```

### provide/inject

## slot


# API
## axios와 mixins


# WebStorage: sessionStorage와 localStorage
- HTML5부터 지원하는 기능
- 키:값 형태로 data를 저장하는 자료구조
- sessionStorage
  - 브라우저 탭 단위로 저장공간 공유
    - 브라우저 메모리 사용
  - 새로고침을 해도 남아있지만, 탭 종료시 사라진다(휘발성)
- localStorage
  - 브라우저 창 단위로 저장공간 공유
  - 브라우저를 닫아도 남아 있다(영속성)
- xss 공격에 취약하다

## xss 취약점
- Cross Site Scripting
  - css는 이미 다른 의미로 많이 사용하고 있어 xss라 줄여 부르는듯 하다
- input 태그 같이 사용자 입력 가능 태그에 script 태그로 코드를 작성하여 

### 대응책


# sessionCookie와 persistentCookie
- server에서 response header에 Set-Cookie란 키로 cookie를 담아 보낸다. 
  - cookie는 string만 담을 수 있다
- 브라우저는 이후 cookie가 사라질때 까지 request header에 Cookie란 키로 cookie를 담아 보낸다 
- sessionCookie
  - 만료일자를 지정하지 않은 쿠키
  - 브라우저를 닫을 시 사라진다
    - 브라우저 메모리 사용
- persistentCookie(permanentCookie)
  - 만료일자를 지정한 쿠키
  - 만료일까진 브라우저를 닫아도 남아있다
    - 디스크 사용
- http only secure cookie
  - 브라우저에서 js로 쿠키에 접근하는걸 막는 설정

## csrf 취약점
  - Cross Site Resource Forgery

### 대응책

# vuex
- npm install vuex@next --save
- 새로고침과 탭 종료시 날라간다
  - js가 동작하는 메모리 공간에 존재

```js



```

## vuex-persistedstate
- vuex에 저장 중인 데이터를 sessionStorage나 localStorage로 옮기는 라이브러리
- sessionStorage로 옮기면 새로고침을 해도 사라지지 않게 할 수 있다. 탭 종료시에는 사라진다
- localStorage로 옮기면 창을 닫아도 사라지지 않는다


## State

## Mutations

## Actions


# accessToken과 RefreshToken 관리