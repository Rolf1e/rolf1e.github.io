### Spring caching

// TODO
```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
</dependencies>
```

Encore une annotation ! `@EnableCaching` active le cache pour Spring.

```java

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean // pas utile avec spring boot
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("addresses");
    }
}
```

Maintenant que le cache est activé, il suffit d'annoter les méthodes que l'on veut cacher avec `@Cacheable("addresses")`
et magique Spring se débrouille ! Par la suite, il faut utiliser `@CacheEvict` et `@CachePut` pour l'administrer comme
souhaité.

#### Avec Google Guava

Pour des besoins plus spécifiques, le projet [Guava](https://github.com/google/guava/wiki/CachesExplained) possède un
système de cache performant et facile d'utilisation.

```java
Cache<Key, Value> cache=CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build(); 
```

Cela s'utilise comme une `Map<K,V>` Java par la suite !

