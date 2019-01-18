### springboot-undertor服务器

#### 说明
```$xslt
默认情况下，Spring Boot 使用 Tomcat 来作为内嵌的 Servlet 容器 
可以将 Web 服务器切换到 Undertow 来提高应用性能。
Undertow 是一个采用 Java 开发的灵活的高性能 Web 服务器，提供包括阻塞和基于 NIO 的非堵塞机制。
Undertow 是红帽公司的开源产品，是 Wildfly 默认的 Web 服务器。
```
#### 步骤
##### 1.移除springboot默认内置服务器
```$xslt
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

```
##### 2.导入undertor依赖
```$xslt
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-undertow</artifactId>
</dependency>

```