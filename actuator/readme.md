### springboot-热部署-Actuator

#### 说明
```
Actuator是spring boot的一个附加功能,可帮助你在应用程序生产环境时监视和管理应用程序。
可以使用HTTP的各种请求来监管,审计,收集应用的运行情况.特别对于微服务管理十分有意义.
缺点：没有可视化界面。
```
#### 实现方式
##### 第1步

pom.xml中引用maven依赖
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
PS: 在maven导入过程可能micrometer-core无法下载，  
此时手工从maven中央仓库下载jar放到本地仓库即可
##### 第2步
application.yml配置
```$xslt
###通过下面的配置(management..)启用所有的监控端点，默认情况下，这些端点是禁用的；
management:
  endpoints:
    web:
      exposure:
        include: "*"
        
        
spring:
  profiles:
    active: prod
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test
    username: root
    password: root
itmayiedu: 
   name: liuwc
```
##### 第4步 Actuator常用访问路径

```
| 路径                  | 作用                                         |    
| --------------------- | -------------------------------------------- | 
| /actuator/beans       | 显示应用程序中所有Spring bean的完整列表。    | 
| /actuator/configprops | 显示所有配置信息。                           |
| /actuator/env         | 陈列所有的环境变量。                         |
| /actuator/mappings    | 显示所有@RequestMapping的url整理列表。       |
| /actuator/health      | 显示应用程序运行状况信息 up表示成功 down失败 |
| /actuator/info        | 查看自定义应用信息                           |
```
示例： /actuator/info（查看自定义应用信息 ）
```$xslt
1)application.yml中配置：   
    info:
      itmayiedu: yushengjun
      addres: www.itmayiedu.com
2)启动后请求URL写 : IP:端口//actuator/info  可以输出配置文件中自定义配置的应用信息
```

#### 总结
Actuator缺点没有界面，一般会使用在此基础上开发的Admin-UI呈现可视化界面