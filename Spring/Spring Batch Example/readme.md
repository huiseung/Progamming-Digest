# example0
- spring batch class, interface 내부 확인용

# example3
## Job1
- 반복주기: 매일 정해진 시간
- ItemReader: 처리하지 않은 파일
- ItemProcess: 없음
- ItemWriter: DB

## Job2
- 반복주기: 
- ItemReader: DB
- ItemProcess: Partitioning 
- ItemWriter: API & FlatFileWriter 