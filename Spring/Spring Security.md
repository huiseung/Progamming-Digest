# Filter
## UsernamePasswordAuthenticationFilter
```java
class UsernamePasswordAuthenticationFilter{
  public void doFilter(
    ServletRequest req,
    ServletResponse res,
    FilterChain chain
  ) throws IOException, ServletException{
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    if(!requiresAuthentication(request, response)){
      chain.doFilter(request, response);
      return;
    }

    authResult = attemptAuthentication(request, response);
    if(authResult == null){
      return;
    }
    sessionStrategy.onAuthentication(authResult, request, response);
  }

  public Authentication attemptAuthentication(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws AuthenticationException{
    if(!request.getMethod().equals("POST")){
      throw 
    }

    String username = obtainUsername(request);
    String password = obtainPassword(request);

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordauthenticationToken(
      username,
      password
    );
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }
}
```
### UsernamePasswordAuthenticationToken

```java
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

```

### AuthenticationManager
```java
public interface AuthenticationManager{
  Authentication authenticate(Authentication authentication)
}

public class ProviderManager implements AuthenticationManager{
  for(AuthenticationProvider provider : getProviders()){

  }
}
```
### AuthenticationProvider
```java
public interface AuthenticationProvider{

}
```

### UserDetailsService

### UserDetails

### SecurityContextHolder