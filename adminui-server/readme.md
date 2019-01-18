## springboot：Admin-UI(监控中心)

### 说明
```
Admin-UI 基于Actuator实现能够返回界面展示监控信息

```
1.什么是spring监控中心？
```$xslt
微服务服务器监控，服务器内存变化（堆内存，线程，日志管理等），
检测服务配置连接地址是否可用（模拟访问，懒加载），
统计现有bean（是spring容器中bean），
统计springMVC @RequestMapping(HTTP接口)
```
### 实现方式说明
```$xslt
1) Admin-UI分为server和client两个方面。
2) 在相对应的server和client导入相对应的maven依赖。
3) 需要注意，在Admin-UI-Client的applicable.yml中配置需要启用所有的监控端点：
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
### 1.Admin-UI-server配置

#### 1.1 pom.xml中引用maven依赖
```$xslt
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<!-- Spring Boot Actuator对外暴露应用的监控信息，Jolokia提供使用HTTP接口获取JSON格式 的数据 -->
<dependency>
    <groupId>org.jolokia</groupId>
    <artifactId>jolokia-core</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>com.googlecode.json-simple</groupId>
    <artifactId>json-simple</artifactId>
    <version>1.1</version>
</dependency>

```
#### 1.2 application.yml配置
```$xslt
spring:
  application:
    name: spring-boot-admin-server
```
#### 1.3 项目启动类上注解 @EnableAdminServer
示例：
```$xslt
@EnableAdminServer
```

### 2.Admin-UI-client配置

#### 2.1 pom.xml中引用maven依赖
```$xslt
<dependency>
                <groupId>de.codecentric</groupId>
                <artifactId>spring-boot-admin-starter-client</artifactId>
                <version>2.0.0</version>
		</dependency>
		<dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
                <groupId>org.jolokia</groupId>
                <artifactId>jolokia-core</artifactId>
		</dependency>
		<dependency>
                <groupId>com.googlecode.json-simple</groupId>
                <artifactId>json-simple</artifactId>
                <version>1.1</version>
		</dependency>

```
#### 2.2 application.yml配置
```$xslt
spring:
  boot:
    admin:
      client:
        url: http://localhost:8080
server:
  port: 8081
  
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS

```
#### 2.3 client项目启动类上不需要额外注解

