
# 목차
- [목차](#목차)
- [Service](#service)
- [Controller](#controller)
  - [API 공통 응답](#api-공통-응답)
  - [Request](#request)

# Service

# Controller

## API 공통 응답
- 프론트 부분에 통일을 위해 

## Request
- @RequestBody
- @RequestParam
  - 쿼리파라미터는 웹서버 로그나 브라우저 히스토리 캐시에 저장될 가능성이 있다. 노출되지 않길 바라는 정보는 쿼리파라미터가 아닌 request body를 이용해 요청을 보내는게 안전하다.
- @PathVariable
