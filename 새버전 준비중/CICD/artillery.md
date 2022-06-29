# Artillery
- 부하 테스트 소프트웨어

## 설치
```
npm install artillery
```

## yaml 설정 파일

```yaml
config:
    target: http://인스턴스외부ip
    phase:
        duration: 성능 측정 시간
        arrivalRate: 초당 신규 유저 수
        name:
scenarios:
    name: 
    flow:
        get:
            url:

```

## 실행
