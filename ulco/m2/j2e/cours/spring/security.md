## Spring Security

Spring possède une couche d'authentification. Grâce à SpringBoot, il n'y a quasiment rien à configurer !

[Documentation officielle](https://docs.spring.io/spring-security/reference/servlet/getting-started.html)

Il faut ajouter cette dépendance à notre pom.xml.

```groovy

implementation 'org.springframework.boot:spring-boot-starter-security'
```

En ajoutant ces deux lignes, si on relance l'application Spring, elle démarre avec le message suivant.

![spring security 1](../images/spring-security-1.png)

En essayant de retourner sur notre application, Spring nous demande de nous authentifier.
Vous pouvez utiliser l'utilisateur `user` et le mot de passe dans votre console.
![spring security login](../images/spring-security-login.png)

Bien évidement, ce n'est pas un comportement que l'on souhaite en production !

Il est possible de
simplement [changer le mot de passe maître](https://www.baeldung.com/spring-boot-security-autoconfiguration).

### Basic Authentication

Spring permet en quelques méthodes de mettre en authentification simple avec Basic par exemple.

Il faut pour cela définir deux `Bean`s.

```java

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
```

Ce code déclare un store en mémoire contre lequel, on peut venir identifier nos requêtes HTTP. (à ne pas utiliser en
production bien évidement !).

Voici
la [documentation](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/provisioning/JdbcUserDetailsManager.html)
officielle de l'implémentation à utiliser en production et un exemple d'
utilisation https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html#servlet-authentication-jdbc-bean

```java
class Config {
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}

class UserService {
    private UserDetailsManager userDetailsManager;
    
    void createUser(UserDTO user) {
      UserDetails jdbcUser = User.builder()
              .username(user.username())
              .password(user.password()) // method should transform password to bcrypt maybe with a Password Manager
              .roles(user.roles())
              .build();
      userDetailsManager.createUser(jdbcUser);
    }
}

```

Cette deuxième méthode permet de modifier la sécurité de nos routes. JEE, et donc Spring, utilisent
une [chaîne de responsabilités](https://refactoring.guru/design-patterns/chain-of-responsibility)
appelée [Filter](https://jakarta.ee/specifications/servlet/5.0/apidocs/jakarta/servlet/filter)
et [SecurityFilterChain](https://docs.spring.io/spring-security/site/docs/3.1.4.RELEASE/reference/security-filter-chain.html).

```java

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
```

Il ne faut pas oublier de désactiver la configuration
automatique `@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })`.

Ici, les toutes routes seront protégées par basic en désactivant les CSRF.

### Filters

Il est possible de définir ses propres filtres pour effectuer des actions techniques, comme l'authentification, du
logging, monitoring, conversion etc.

Le `CorsFilter` de Spring est un bon exemple à étudier.

```java
class SecurityConfig {
    @Bean
    public CorsFilter corsFilter() {
        final var source = new UrlBasedCorsConfigurationSource();
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.addExposedHeader("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Location");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

```

Ici, il n'est pas utile de venir le placer dans la chaîne, mais si habituellement, il faut l'introduire vu la
variable `HttpSecurity http` avec la méthode `addFilterAfter(filter, Class<OtherFilter>)`

### SecurityContext

Spring met à disposition pendant toute la durée de la requête HTTP une variable essentielle: `SecurityContext`
accessible via `SecurityContextHolder`. Il est le moyen pour obtenir une variable de type `Authentification` qui
représente l'utilisateur. Il peut être populé, généralement au début de requête dans les `Filter`s.

```java
final var securityContext=SecurityContextHolder.getContext();
final var auth=securityContext.getAuthentication();
log.info("Hello from {}, {}, {}",auth.getName(),auth.getDetails(),auth.getPrincipal());
```
