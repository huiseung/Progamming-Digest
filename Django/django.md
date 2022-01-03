# 목차
- [목차](#목차)
- [프로젝트 구조](#프로젝트-구조)
  - [설치](#설치)
  - [Project](#project)
  - [App](#app)
  - [Nginx(WAS)+Gunicorn(WSGI)](#nginxwasgunicornwsgi)
    - [Gunicorn](#gunicorn)
    - [Nginx](#nginx)
  - [MySQL(DataBase)](#mysqldatabase)
- [API](#api)
  - [model](#model)
  - [Seirialize](#seirialize)
    - [serializers](#serializers)
    - [admin 등록](#admin-등록)
  - [views](#views)
    - [reuqest](#reuqest)
      - [parsing](#parsing)
      - [authentication](#authentication)
      - [content negotiation](#content-negotiation)
    - [viewsets](#viewsets)
    - [permissions](#permissions)
  - [contrib.auth.models](#contribauthmodels)
    - [User](#user)
    - [Group](#group)
  - [Pagination](#pagination)

# 프로젝트 구조
## 설치
- pip install django
- pip install gunicorn
- pip instal djangorestframework
  
## Project
- 프로젝트 생성: django-admin startproject config .
  - settings.py에 코드 변경
    - LANGUAGE_CODE = 'ko-kr'
    - TIME_ZONE = 'Asia/Seoul'
- 앱들이 필요로 하는 테이블 생성
  - 모델기반 미그레이션 생성: python manage.py makemigrations
  - 미그레이션 작업 쿼리 보기: python manage.py sqlmigrate 앱이름 작업번호
  - 미그레이션기반 테이블 생성: python manage.py migrate 
- 관리자 생성: python manage.py createsuperuser
- 프로젝트 실행: python mange.py runserver
  - 서버 시작 문구: Starting development server at http://127.0.0.1:8000

```
|-config
    |-__init__.py
    |-asgi.py
    |-settings.py
    |-urls.py
    |-wsgi.py
|-mange.py
|-db.sqlite3 //서버 실행시 생성된다
```

## App
- 앱 생성: python manage.py startapp 앱이름
- 프로젝트 urls에 등록
  - path("앱이름/", include("앱이름.urls"))
- 프로젝트 settings에 등록
  - INSTALLED_APPS = ['앱이름.apps.앱이름Config", ]
```
앱이름
    |-migrations
    |-__init__.py
    |-admin.py
    |-apps.py
    |-models.py
    |-tests.py
    |-vies.py
    |-serializers.py //별도로 만들어 준다
    |-urls.py //별도로 만들어 준다

```

## Nginx(WAS)+Gunicorn(WSGI)
- runserver는 개발용으로 사용하고 실 배포시에는 별도에 서버 소프트웨어를 사용하자

### Gunicorn
- gunicorn --bind 0:8000 앱이름.wsgi:appplication

### Nginx

## MySQL(DataBase)
- db.sqlite3은 개발용으로 사용하고 실 배포시에는 별도에 데이터베이스를 사용하자



# API
## model
```python
#app/models.py
from django.db import models

class 모델명(models.Model):
    pass

```

## Seirialize
```python
#app/serializers.py
from rest_framework import serializers
from .models import *

class 모델명Serializer(serializers.ModelSerializer):
    class Meta:
        model = 모델명
        fields = ("필드1", )
```

### serializers
- Serializer
```python
class SnippetSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    title = serializers.CharField(required=False, allow_blank=True, max_length=100)
    code = serializers.CharField(style={'base_template': 'textarea.html'})
    linenos = serializers.BooleanField(required=False)
    language = serializers.ChoiceField(default='python')
    style = serializers.ChoiceField(default='friendly')

    def create(self, validated_data):
        """
        Create and return a new `Snippet` instance, given the validated data.
        """
        return Snippet.objects.create(**validated_data)

    def update(self, instance, validated_data):
        """
        Update and return an existing `Snippet` instance, given the validated data.
        """
        instance.title = validated_data.get('title', instance.title)
        instance.code = validated_data.get('code', instance.code)
        instance.linenos = validated_data.get('linenos', instance.linenos)
        instance.language = validated_data.get('language', instance.language)
        instance.style = validated_data.get('style', instance.style)
        instance.save()
        return instance
```
- ModelSerializer: Serializer보다 훨씬 코드가 짧다
```python
class SnippetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Snippet
        fields = ['id', 'title', 'code', 'linenos', 'language', 'style']

```
- HyperlinkedModelSerializer




### admin 등록
```python
#app/admin.py
from .models import *

class 모델명Admin(admin.ModelAdmin):
    search_fields = ['필드명']

admin.site.register(모델명, 모델명Admin)

```

## views
- djangorestframework 사용 안하고 end point 만들기
```python
@csrf_exempt
def snippet_list(request):
    """
    List all code snippets, or create a new snippet.
    """
    if request.method == 'GET':
        snippets = Snippet.objects.all()
        serializer = SnippetSerializer(snippets, many=True)
        return JsonResponse(serializer.data, safe=False)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = SnippetSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)

@csrf_exempt
def snippet_detail(request, pk):
    """
    Retrieve, update or delete a code snippet.
    """
    try:
        snippet = Snippet.objects.get(pk=pk)
    except Snippet.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = SnippetSerializer(snippet)
        return JsonResponse(serializer.data)

    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = SnippetSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        snippet.delete()
        return HttpResponse(status=204)

```


- djangorestframework 사용해 end point 만들기
```python
#app/views.py
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import viewsets
from rest_framework import permissions

from models import *
from serializers import *


@api_view(['GET'])
def api이름(request):
    return Response()

```

```python
#app/urls.py
from django.urls import path
from .views import *

urlpatterns = [
    path("경로명", 함수명, name="함수명"),
]

```

### reuqest
#### parsing
- .data
- .query_params
- .parasers
#### authentication
- .user
- .auth
- .authenticators

#### content negotiation
- .accepted_render
- .accepted_media_type



### viewsets
- ModelViewSet

### permissions
- IsAuthenticated

## contrib.auth.models
### User

### Group

## Pagination
```python
#project/settings.py
REST_FRAMEWORK = {
    "DEFAULT_PAGINATION_CLASS": "rest_framework.pagination.PageNumberPagination",
    "PAGE_SIZE": 10
}
```


