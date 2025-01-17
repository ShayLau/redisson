# Redis integration with Micronaut

Integrates Redisson with [Micronaut](https://micronaut.io/) framework.  

Supports Micronaut 2.0.x - 2.5.x  

## Usage  

### 1. Add `redisson-micronaut` dependency into your project:  

Maven  

```xml  
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson-micronaut</artifactId>
    <version>3.16.0</version>
</dependency>
```

Gradle

```groovy
compile 'org.redisson:redisson-micronaut:3.16.0'
```

### 2. Add settings into `application.yml` file

#### 2.1 Redisson settings

Config structure is a Redisson YAML configuration - 
[single mode](https://github.com/redisson/redisson/wiki/2.-Configuration#262-single-instance-yaml-config-format),
[replicated mode](https://github.com/redisson/redisson/wiki/2.-Configuration#252-replicated-yaml-config-format),
[cluster mode](https://github.com/redisson/redisson/wiki/2.-Configuration#242-cluster-yaml-config-format),
[sentinel mode](https://github.com/redisson/redisson/wiki/2.-Configuration#272-sentinel-yaml-config-format),
[proxy mode](https://github.com/redisson/redisson/wiki/2.-Configuration#292-proxy-mode-yaml-config-format)

NOTE: Setting names in camel case should be joined with hyphens (-).

Config example:
```yaml
redisson:
  single-server-config:
     address: "redis://127.0.0.1:6379"
  threads: 16
  netty-threads: 32
```

#### 2.2 Cache settings

|Setting name|Type|Description|
|------------|----|-----------|
|redisson.caches.*.max-size|java.lang.Integer|Max size of this cache. Superfluous elements are evicted using LRU algorithm. If <code>0</code> the cache is unbounded (default).|
|redisson.caches.*.codec|java.lang.Class|Redis data codec applied to cache entries. Default is MarshallingCodec codec.|
|redisson.caches.*.expire-after-write|java.time.Duration|Cache entry time to live duration applied after each write operation.|
|redisson.caches.*.expire-after-access|java.time.Duration|Cache entry time to live duration applied after each read operation.|

Config example:
```yaml
redisson:
  single-server-config:
    address: "redis://127.0.0.1:6379"
  caches:
    my-cache1: 
      expire-after-write: 10s
      expire-after-access: 3s
      max-size: 1000
      codec: org.redisson.codec.MarshallingCodec
    my-cache2: 
      expire-after-write: 200s
      expire-after-access: 30s
```

#### 2.3 Session settings

[Session](https://docs.micronaut.io/latest/api/io/micronaut/session/Session.html) store implementation.
Additional settings to [HttpSessionConfiguration](https://docs.micronaut.io/2.5.4/api/io/micronaut/session/http/HttpSessionConfiguration.html) object:

|Setting name|Type|Description|
|------------|----|-----------|
|micronaut.session.http.redisson.enabled|java.lang.Boolean|Enables Session store|
|micronaut.session.http.redisson.key-prefix|java.lang.Integer|Defines string prefix applied to all objects stored in Redis.|
|micronaut.session.http.redisson.codec|java.lang.Class|Redis data codec applied to cache entries. Default is MarshallingCodec codec.|
|micronaut.session.http.redisson.update-mode|java.lang.String|Defines session attributes update mode.<br/>`WRITE_BEHIND` - session changes stored asynchronously.<br/>`AFTER_REQUEST` - session changes stored only on `SessionStore#save(Session)` method invocation. Default value.|
|micronaut.session.http.redisson.broadcastSessionUpdates|java.lang.Boolean|Defines broadcasting of session updates across all micronaut services.|


Config example:

```yaml
micronaut:
    session:
        http:
            redisson:
                enabled: true
                update-mode: "WRITE_BEHIND"
                broadcast-session-updates: false
```
### 3 Use Redisson

#### 3.1 Redisson instance

```java
@Inject
private RedissonClient redisson;
```

#### 3.2 Cache

```java
@Singleton 
@CacheConfig("my-cache1") 
public class CarsService {

    @Cacheable
    public List<String> listAll() {
        // ...
    }
    
    @CachePut(parameters = {"type"}) 
    public List<String> addCar(String type, String description) {
        // ...
    }
    
    @CacheInvalidate(parameters = {"type"}) 
    public void removeCar(String type, String description) {
        // ...
    }    
}
```

Consider __[Redisson PRO](https://redisson.pro)__ version for **ultra-fast performance** and **support by SLA**.
