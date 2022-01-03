# 목차
- [목차](#목차)
- [스프링 배치](#스프링-배치)
  - [왜 사용하는가](#왜-사용하는가)
  - [사용 사례](#사용-사례)
  - [main](#main)
    - [설정 클래스들](#설정-클래스들)
      - [SimpleBatchConfiguration extends AbstractBatchConfiguration](#simplebatchconfiguration-extends-abstractbatchconfiguration)
      - [사용자 정의 JobConfiguration](#사용자-정의-jobconfiguration)
      - [BatchAutoConfiguration](#batchautoconfiguration)
  - [Job and Step](#job-and-step)
    - [SimpleJob](#simplejob)
    - [Step](#step)
    - [FlowJob](#flowjob)
      - [@JobScope와 @StepScope](#jobscope와-stepscope)
    - [JobParameter](#jobparameter)
    - [JobInstance](#jobinstance)
    - [JobLauncher](#joblauncher)
    - [JobRepository](#jobrepository)
    - [JobExecution](#jobexecution)
    - [StepExecution](#stepexecution)
    - [StepContribution](#stepcontribution)
    - [ExecutionContext](#executioncontext)
    - [ItemReader&ItemProcessor&ItemWriter](#itemreaderitemprocessoritemwriter)
  - [meta data](#meta-data)
  - [Chunk Process](#chunk-process)
    - [ItemReader](#itemreader)
    - [ItemProcessor](#itemprocessor)
    - [ItemWriter](#itemwriter)
  - [Repeat, FaultTolerant, Skip, Retry](#repeat-faulttolerant-skip-retry)
    - [Repeat](#repeat)
    - [FaultTolerant](#faulttolerant)
    - [Skip](#skip)
    - [Retry](#retry)
  - [MultiThread](#multithread)
    - [AsyncItemProcess/Writer](#asyncitemprocesswriter)
    - [MultiThreadStep](#multithreadstep)
    - [ParallelStep](#parallelstep)
    - [Partitioning](#partitioning)
    - [SynchronizedItemStreamReader](#synchronizeditemstreamreader)
  - [EventListener](#eventlistener)
  - [Spring Batch Test](#spring-batch-test)

# 스프링 배치
## 왜 사용하는가
- 호출 빈도는 낮지만, 호출시 처리 시간이 오래 걸리는 작업을 API server에서 진행하는건 자원 낭비다
- 배치 처리를 위한 표준 아키테처
- 배치 처리: 대용량 데이터를 배치단위로 나눠 동일 작업을 반복
- 자동화: 심각한 문제를 제외하고 사용자 개입 없이 지속적으로 동작
- 신뢰: 문제 발생시 추적 가능, 작업 도중 실패시, 실패지점 부터 이어서 재작업 

## 사용 사례
- 일매출 집계
  - 배치작업을 통해 일 매출 집계 데이터를 만들어 둬, 사용자가 일 매출 집계 조회시 별도에 집계 처리없이 조회 가능하게 한다


## main

```java
@EnableBatchProcessing //배치 기능 사용하기
@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
```

- @EnableBatchProcessing
  - 설정 클래스들을 실행
  - 스프링 배치 초기화
  - Job 클래스 초기화, 실행

### 설정 클래스들
#### SimpleBatchConfiguration extends AbstractBatchConfiguration 

- 메서드
- void initialize()
- JobExplorer jobExplorer()
- JobLauncher jobLauncher()
- JobRegistry jobRegistry()
- JobRepository jobRepsitory()
- PlatformTransactionManager transactionManager()
<br></br>
- AbstractBatchConfiguration에서 상속한 메서드
- afterPropertiesSet()
- getConfigurer()
- jobBuilders()
- setImportMetadata()
- stepBuilders()


#### 사용자 정의 JobConfiguration
- 사용할 Job과 Step을 Bean으로 등록하는 클래스

```java
//example
@RequiredArgsConstructor
@Configuration
public class JobConfig{
	private fianl JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job myJob(){
		return jobBuilderFactory.get("myJob")
			.start(myStep1())
			.next(myStep2())
			.build();
	}

	@Bean
	public Step myStep1(){
		return stepBUilderFactory.get("myStep1")
			.tasklet(((contribution, chunkContext)->{
				return RepeatStatus.FINISHED;
			}))
			.build();
	}
}

```

#### BatchAutoConfiguration

## Job and Step
- JobBuilderFactory
- StepBuilderFactory

### SimpleJob
- start()
- next()
- validator()
- preventRestart()
- incrementer()

### Step
- tasklet()
- startLimit()
- allowStartIfComplete()

### FlowJob
- start()
- next()
- enum status
  - BatchSTatus
  - ExitStatus
  - FlowExecutionStatus
- on()
- to()
- stop()
- fail()
- end()
- stopAndRestart()

#### @JobScope와 @StepScope

### JobParameter

### JobInstance
- Job 설정은 동일하지만 실행시 처리하는 데이터, 내용이 다르기 때문에 이를 구분하는 용도에 객체
- BATCH_JOB_INSTANCE table에 매핑된다
- JobLauncher가 Job 실행시, 처음 시작하는 (Job, Job Parameter)일 경우 JobRepository는 JobInstance를 생성하고, 이전과 동일할 경우 존재하는 JobInstance를 반환

### JobLauncher
- Job과 Job Parameters 객체를 받아 실행(run)시키는 class
### JobRepository
- meta data 관리

### JobExecution


### StepExecution
### StepContribution
### ExecutionContext

### ItemReader&ItemProcessor&ItemWriter


## meta data

![reference: https://docs.spring.io/spring-batch/docs/3.0.x/reference/html/metaDataSchema.html](./image/batch_metadata.PNG)

- sprinb batch안에는 schema-*.sql 형태로 meta data table을 생성하는 스키마 코드가 들어있다. 사용하고픈 database에 맞춰 사용하면 된다
<br></br>
- Job 관련 테이블
- BATCH_JOB_INSTANCE: Job 실행시 Job Parameter에 따라 job_name, job_key(Job Parameter에 해쉬값) 이 저장 된다
- BATCH_JOB_EXECUTION: Job 실행 정보가 저장되는 테이블. 생성시간, 시작시간, 종료시간, 실행상태, 메세지
- BATCH_JOB_EXECUTION_PARAMS: Job parameter 정보
- BATCH_JOB_EXECUTION_CONTEXT: step간 공유 가능한 데이터를 Jso 형태로 저장
- BATCH_JOB_SEQ
- BATCH_JOB_EXECUTION_SEQ
<br></br>
- Step 관련 테이블
- BATCH_STEP_EXECUTION: step 실행 정보.
- BATCH_STEP_EXECUTION_CONTEXT: step별 데이터를 json형태로 저장
- BATCH_STEP_EXECUTION_SEQ

## Chunk Process
### ItemReader
### ItemProcessor
### ItemWriter

## Repeat, FaultTolerant, Skip, Retry
### Repeat
### FaultTolerant
### Skip
### Retry

## MultiThread
### AsyncItemProcess/Writer
### MultiThreadStep
### ParallelStep
### Partitioning
### SynchronizedItemStreamReader

## EventListener

## Spring Batch Test
