# Esper Spring Boot

Contains autoconfiguration for the Esper CEP engine. The Esper CEP engine is a powerful and mature solution for complex event processing. 

Spring Boot is a powerful and mature solution for building services on the JVM. 

This module aims to unify the two of them.

## Build Configuration

This dependency is intended for Spring Boot 3.0. 

Add the dependency to your build: 

```xml 
    <dependency>
        <groupId>com.joshlong</groupId>
        <artifactId>esper-spring-boot-starter</artifactId>
        <version>0.0.1</version>
    </dependency>
```

Spring Boot manages the version of some libraries that this library in turn depends on. So, you'll need to override the version managed by Spring Boot and _downgrade_ it. 

### Apache Maven
Override the property in the `<properties>` stanza of your application's build:

```xml 
<properties>
    <!-- ... -->
    <janino.version>3.1.0</janino.version>
</properties>
``` 

### Gradle

Override the project's `ext[]` dictionary key for the property:

```groovy 
project.ext["janino.version" ] = '3.1.6'
```


## Java 

At a minimum you'll probably want to contribute the `.class` literal definitions for your event types to the Esper `Configuration` (which lives in a different package than Spring's `@Configuration`) object by defining a bean of type `EsperConfigurationCustomizer`.

You'll then want to use the autoconfigured `EPCompiler`, `EPDeploymentService`, etc., to define and deploy some queries and their handlers, perhaps in an `InitializingBean`?

Here's [a more full-fledged example](https://github.com/coffee-software-show/complex-event-processsing-with-esper).

## License

Normally, I'd make my libraries Apache 2 Licensed, but this library links to Esper, which in turn is GPL2. Please be aware of this before linking to this library! 


