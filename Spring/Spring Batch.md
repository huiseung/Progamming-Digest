# 목차
- [목차](#목차)
- [스프링 배치](#스프링-배치)
  - [왜 사용하는가](#왜-사용하는가)
  - [사용 사례](#사용-사례)
- [시작](#시작)
  - [설정 클래스들](#설정-클래스들)
    - [SimpleBatchConfiguration](#simplebatchconfiguration)
    - [사용자 정의 JobConfiguration](#사용자-정의-jobconfiguration)
    - [BatchAutoConfiguration](#batchautoconfiguration)
    - [BatchProperties](#batchproperties)
- [Job](#job)
  - [JobLauncherApplicationRunncer](#joblauncherapplicationrunncer)
  - [JobBuilder](#jobbuilder)
  - [JobInstance](#jobinstance)
  - [JobParameters](#jobparameters)
  - [JobExecution](#jobexecution)
  - [JobExecutionDecider](#jobexecutiondecider)
  - [JobLauncher](#joblauncher)
  - [JobRepository](#jobrepository)
    - [meta data table](#meta-data-table)
  - [Job 구현체](#job-구현체)
    - [SimpleJob](#simplejob)
    - [FlowJob](#flowjob)
- [Step](#step)
  - [StepBuilder](#stepbuilder)
  - [StepExecution](#stepexecution)
  - [StepContribution](#stepcontribution)
  - [Step 구현체](#step-구현체)
    - [JobStep](#jobstep)
    - [FlowStep](#flowstep)
    - [TaskletStep](#taskletstep)
    - [PartitionStep](#partitionstep)
  - [ExecutionContext](#executioncontext)
  - [JobScope와 StepScope](#jobscope와-stepscope)
- [Chunk Process](#chunk-process)
  - [ItemReader](#itemreader)
  - [ItemWriter](#itemwriter)
  - [ItemProcessor](#itemprocessor)
- [Repeat, FaultTolerant, Skip, Retry](#repeat-faulttolerant-skip-retry)
  - [Repeat](#repeat)
  - [FaultTolerant](#faulttolerant)
  - [Skip](#skip)
  - [Retry](#retry)
- [MultiThread at SpringBatch](#multithread-at-springbatch)
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
- 새롭게 파일들이 추가되는 환경, 매일 정해진 시간에 파일들을 읽어 db에 저장한다. 이미 db에 저장된 파일은 작업하지 않는다
- 
- 일매출 집계
  - 배치작업을 통해 일 매출 집계 데이터를 만들어 둬, 사용자가 일 매출 집계 조회시 별도에 집계 처리없이 조회 가능하게 한다

------

# 시작
- 라이브러리 추가: implementation 'org.springframework.boot:spring-boot-starter-batch'

```java
//main application class
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
    - SimpleBatchConfiguration
    - 사용자 정의 JobConfig
    - BatchAutoConfiguration
  - 스프링 배치 초기화
    - BatchProperties
  - Job 클래스 초기화, 실행

## 설정 클래스들

### SimpleBatchConfiguration
- Job과 관련한 Bean 객체 등록

```java
//메서드
void initialize()
@Bean
JobExplorer jobExplorer()
@Bean
JobLauncher jobLauncher()
@Bean
JobRegistry jobRegistry()
@Bean
JobRepository jobRepsitory()
@Bean
PlatformTransactionManager transactionManager()


@Bean
JobBuilderFactory jobBuilders()
@Bean
StempBuilderFactory stepBuilders()
void setImportMetadata()
void afterPropertiesSet()
BatchConfigurer getConfigurer()

```

### 사용자 정의 JobConfiguration
- 사용할 Job과 Step을 Bean으로 등록하는 클래스

```java
//example
@RequiredArgsConstructor
@Configuration
public class JobConfig{
	private final JobBuilderFactory jobBuilderFactory;
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
		return stepBuilderFactory.get("myStep1")
			.tasklet(((contribution, chunkContext)->{
				return RepeatStatus.FINISHED;
			}))
			.build();
	}
}

```

### BatchAutoConfiguration
- JobLauncherApllicationRunner를 Bean으로 등록하는 클래스

```java
@Configuration(proxyBeanMethods = false)
class BatchAutoConfiguration{
  @Bean
  public JobLauncherApplicationRunner jobLauncherApplicationRunner(JobLauncher jobLauncher, JobExplorer jobExplorer,
			JobRepository jobRepository, BatchProperties properties)
}

```

### BatchProperties
```java
@ConfigurationProperties(prefix = "spring.batch")
class BatchProperties{
  
}
```

------

# Job
## JobLauncherApplicationRunncer
```java
class JobLauncherApplicationRunner{

}
```


## JobBuilder
```java
public class JobBuilderFactory{
  private final JobRepository jobRepository;

  public JobBuilder get(string name)
}

public class JobBuilder{
  SimpleJobBuilder start(Step step);
  JobFlowBuilder flow(Step step);
  JobFlowBuilder start(Flow flow);
}
```


## JobInstance
- 논리적 Job 단위 객체
  - 같은 Job 객체라도 실행시 처리하는 내용(JobParameter)은 다르다
  - Job + JobParameter
- BATCH_JOB_INSTANCE table에 매핑된다
- JobLauncher가 Job 실행시, 처음 시작하는 (Job, Job Parameter)일 경우 JobRepository는 JobInstance를 생성하고, 이전과 동일할 경우 존재하는 JobInstance를 반환한다


## JobParameters
- Job 살행시 설정값을 나타내는 객체
- BATCH_JOB_EXECUTION_PARAMS table에 매핑된다

```java
public class JobParameters{
  LinkedHashMap<String, JobParameter> parameters;
}

public class JobParameter{
  T parameter;
  boolean identifying;
}

//생성 예제
JobParamters jobParameters = JobParametersBuilder()
  .addString("key", "value")
  .addDate("key", new Date().now())
  .addLong("key", 1L)
  .addDouble("key", 0.1)
  .toJobParameters();

```
  
## JobExecution
- Job 실행을 표현한 객체
- JobInstance 실행시 생성되는 객체
- BATCH_JOB_EXECUTIOn table과 매핑

```java
class JobExecution{
  JobInstance jobInstance;
  JobParameters jobPrameters;
  Collection<StepExecution> stepExecutions;

  String jobConfigurationName
  
  BatchStatus batchStatus;
  /*
  COMPLETED: JobInstance 완료, 동일 JobInstance 재 실행 불가, 실행 시 JobInstanceAlreadyCompleteException
  FAILED: JobInstance 실패, new JobExecution를 통한 JobInstance 재실행 
  STARTING, STARTED, STOPPING, STOPPED, ABANDONED, UNKNOWN
  */
  ExitStatus exitStatus;
  /*
  COMPLETED, FAILED, STOPPED, EXCUTING, NOOP, UNKNOWN
  */

  Date startTime;
  Date createTime;
  Date endTime;
  Date lastUpdated;

  ExcutionContext excutionContext;

  List<Exception> failureExceptions;

}
```

## JobExecutionDecider



## JobLauncher
- Job과 Job Parameters 객체를 받아 실행(run)시킨다

```java
interface JobLauncher{
  JobExeuction run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException,
  JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException;
  //이미 JobInstance가 존재하는 (Job, Job Parameter)를 실행시키면 Exception 발생

}

class JobLauncherApplicationRunner{
  JobLauncher jobLauncher;

  void execute(Job  job, JobParameters jobParameters){
    JobParameters parameters = getNextJobParameters(job, jobParameters);
    JobExcution execution = this.jobLauncher.run(job, parameters);
  }
}

```

## JobRepository
- meta data 관리

```java
public interface JobRepository{
  //Create
  JobExecution createJobExecution(JobInstance jobInstance, JobParameters jobParameters)
  JobInstance createJobInstance(String jobName, JobParameters jobParameters)
  //Read
  JobExecution getLastJobExecution(String jobName, JobParameters jobParameters);
  StepExecution getLastStepExecution(JobInstance jobInstance, String stepName);
  int getStepExecutionCount(JobInstance jobInstance, String stepName);
  //Update
  void update(JobExecution jobExecution);
  void update(StepExeuction stepExeuction);
  void updateExeuctionContext(JobExecution jobExecution);
  void updateExeuctionContext(StepExeuction stepExeuction);
  void add(StepExecution stepExecution)
  void addAll(Collection<StepExcution>stepExecutions)

}
```

### meta data table

![reference: https://docs.spring.io/spring-batch/docs/3.0.x/reference/html/metaDataSchema.html](./image/batch_metadata.PNG)

- sprinb batch안에는 schema-*.sql 형태로 meta data table을 생성하는 스키마 코드가 들어있다. 사용하고픈 database에 맞춰 사용하면 된다
<br></br>
- Job 관련 테이블
- BATCH_JOB_INSTANCE: Job 실행시 Job Parameter에 따라 job_name, job_key(Job Parameter에 해쉬값) 이 저장 된다
- BATCH_JOB_EXECUTION_PARAMS: Job parameter 정보
- BATCH_JOB_EXECUTION: Job 실행 정보가 저장되는 테이블. 생성시간, 시작시간, 종료시간, 실행상태, 메세지
- BATCH_JOB_EXECUTION_CONTEXT: step간 공유 가능한 데이터를 Jso 형태로 저장
- BATCH_JOB_SEQ
- BATCH_JOB_EXECUTION_SEQ
<br></br>
- Step 관련 테이블
- BATCH_STEP_EXECUTION: step 실행 정보.
- BATCH_STEP_EXECUTION_CONTEXT: step별 데이터를 json형태로 저장
- BATCH_STEP_EXECUTION_SEQ


## Job 구현체
### SimpleJob

```java
class SimpleJob{
  //method
  void addStep(Step step)
  void doExecute(JobExecution exeuction)
  Step getStep(String stepName)
  Collection<String> getStepNames()
  void setSteps(List<Step> steps)
}

```

### FlowJob


# Step
## StepBuilder

## StepExecution
- Step에 실행을 표현한 객체
- BATCH_STEP_EXECUTION 테이블에 매핑

```java
class StepExeuction{
  String stepName
  JobExcution parentJobExcution;
  
  BatchStatus batchStatus;
  ExitStatus exitStatus;
  
  int readCount;
  int writeCount;
  int rollbackCount;
  int readSkipCount;
  int processSkipCount;
  int writeSkipCount;
  int filterCount;
  
  Date startTime;
  Date endTime;
  Date lastUpdated;
  
  ExecutionContext executionContext;

  boolean terminateOnly;

  List<Throwable> failureException;
}

```

## StepContribution
- chunk process 변경사항을 가져와 StepExecution 상태 업데이터하는 객체

```java
class StepContribution {
  StepExecution stepExecution;
  int readCount;
  int writeCount;
  int filterCount;
  int parentSkipCount;
  int parentSkipCount;
  int readSkipCount;
  int writeSkipCount;
  int processSkipCount;
  ExitStatus exitStatus;
}
```

## Step 구현체
### JobStep

### FlowStep

### TaskletStep

### PartitionStep


## ExecutionContext
- Excution에 상태를 저장
- JobExcution에 ExecutionContext: JobExcution간 공유 불가, JobExcution에 연관된 StepExcution과 공유 가능 
- StepExcution에 ExecutionContext: StepExcution간 공유

```java
class ExecutionContext implements Serializable{
  boolean dirty;
  Map<String, Object> map;
}
```

## JobScope와 StepScope

------

# Chunk Process
## ItemReader
## ItemWriter
## ItemProcessor

------

# Repeat, FaultTolerant, Skip, Retry
## Repeat
## FaultTolerant
## Skip
## Retry

------
# MultiThread at SpringBatch
## AsyncItemProcess/Writer
## MultiThreadStep
## ParallelStep
## Partitioning
## SynchronizedItemStreamReader

------
# EventListener

------
# Spring Batch Test
