# 목차

- [목차](#목차)
- [애플리케이션 보안](#애플리케이션-보안)
  - [사용자 인증 Authentication](#사용자-인증-authentication)
  - [권한 인가 Authorization](#권한-인가-authorization)
  - [민감정보 암호화](#민감정보-암호화)
  - [Https, SSL, Secure Sockets Layer](#https-ssl-secure-sockets-layer)
- [Spring Security Setting](#spring-security-setting)
  - [의존성 추가](#의존성-추가)
  - [Config](#config)
- [Spring Security Filter](#spring-security-filter)
  - [SecurityFilterChain](#securityfilterchain)
  - [Authentication](#authentication)
  - [AuthenticationManager](#authenticationmanager)
  - [AuthenticationProvider](#authenticationprovider)
  - [AccessDecisionManager](#accessdecisionmanager)
  - [AccessDecisionVoter](#accessdecisionvoter)
  - [SecurityContextHolder](#securitycontextholder)
  - [UserDetailsService](#userdetailsservice)
  - [UserDetails](#userdetails)
- [JWT](#jwt)
  - [Header](#header)
  - [Payload](#payload)
  - [Signature](#signature)
- [OAuth](#oauth)

------------------------

# 애플리케이션 보안
## 사용자 인증 Authentication
- 아이디기반 로그인
- 익명 영역: 인증 없이 사용 가능한 기능들, 시스템 상태 변경 기능은 제공하지 말아야 한다
- 인증 영역: 인증된 사용자만 사용 가능한 기능들 

## 권한 인가 Authorization
- 데이터에 접근할 수 있는 권한 확인

## 민감정보 암호화
- 비밀번호, 결제정보, 연락처등 민감정보는 데이터베이스가 해킹당해도 텍스트 형태로 바로 노출되지 않도록 한다

## Https, SSL, Secure Sockets Layer



------------

# Spring Security Setting
## 의존성 추가
```
org.springframework.boot:spring-bbot-starter-security
org.springframework.security:spring-security-test
```

## Config
```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter{
  @Override
  public void configure(WebSecurity web){

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
      .authorizeRequests()
      .formLogin()
      .logout()
      .rememberMe()    
  }
}
```

# Spring Security Filter
- Filter는 Servlet에 request가 전달되기 전 혹은 response가 처리된 후에 request/response에 동작한다
- 모든 request/response는 모든 filter를 거치지만, 해당 request/response에 동작할 필요가 없다면 바로 넘긴다

## SecurityFilterChain
- ChannelProcessingFilter
  - request가 http인가 https인가
- SecurityContextPersistenceFilter
  - SecurityContext load/save
- LogoutFilter
  - user logout
- UsernamePasswordAuthenticationFilter
  - 인증 처리
  - Authentication 인터페이에 구현체인 UsernamePasswordAuthenticationToken 객체 생성
  - 생성된 객체를 AuthenticationManager인터페이스(구현체는 ProviderManager)에게 전달
- DefaultLoginPageGeneratingFilter
  - 로그인을 위한 html 생성
- RequestCacheAwareFilter
- SecurityContextHolderAwareRequestFilter
- RememberMeAuthenticationFilter
- AnonymousAuthenticationFilter
  - 여기까지 request가 인증처리되지 않았다면 익명으로 처리
- ExceptionTranslationFilter
  - 예외 처리
- FilterSecurityInterceptor
  - 권한 처리
  - AccessDecisionManager 인터페이스가 Authentication에 속성으로 있는 권한정보를 확인

## Authentication

```java
public abstract class AbstractAuthenticationToken implements Authentication, CredentialsContainer{
  private final Collection<GrantedAuthority> authorities;
  private Object details;
  private boolean authenticated;

  public AbstractAuthenticationToken(
    Collection<? extends GrantedAuthority> authories
  ){
    if(authories == null){
      this.authorities = AuthorityUils.NO_AUTHORITIES;
      return;
    }
    ArrayList<GrantedAuthority> temp = new ArrayList<>(authorities.size());
    temp.addAll(authorities);
    this.authorities = Collections.unmodifiableList(temp);
  }
} 


class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken{
  private final Object principal;
  private Object credentials;

  public UsernamePasswordAuthenticationToken(
    Object principal,
    Object credentials
  ){
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(false);
  }

  public UsernamePasswordAuthenticationToken(
    Object principal,
    Object credentials,
    Collection<? extends GrantedAuthority> authorities
  ){
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(true);
  }
}
```

## AuthenticationManager
- 인증관련 처리

## AuthenticationProvider

## AccessDecisionManager
- 인가관련 처리
```java
public interface AuthenticationManager{
  Authentication authenticate(Authentication authentication)
}

public class ProviderManager implements AuthenticationManager{
  for(AuthenticationProvider provider : getProviders()){

  }
}
```

## AccessDecisionVoter

## SecurityContextHolder

## UserDetailsService

## UserDetails

-------------------------

# JWT
- Header, Payload, Signature 로 구성된다
- 각 부분을 base64로 인코딩하고 . 으로 구분한다
- jwt 자체는 암호화 되어 있지 않기때문에 민감정보는 담지 않는다

## Header
- alg: signature를 해싱한 알고리즘 종류
- typ: jwt임을 명시

## Payload
- key-value 자료구조로된 Claim 객체
- 클라이언트, 서버가 주고 받는 핵심 내용
- iss: 토큰 발급자
- sub: 토큰 제목
- exp: 토큰 만료시간
- iat: 토큰 발급시간

## Signature
- (header + . + payload)를 secretKey로 암호화한값
- secretKey는 server만 알고 있다
- 서버는 받은 jwt에 Signature 부분을 secretKey로 복호화해, 해당 jwt에 Header, Payload와 대조해 위조 변경 사항을 감지한다

-------------

# OAuth