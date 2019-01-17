### springboot-热部署-devtools

#### 说明
所谓的热部署：比如项目的热部署，就是在应用程序在不停止的情况下，实现新的部署

#### 原理
spring-boot-devtools是一个为开发者服务的一个模块。 
其中最重要的功能就是自动应用代码更改到最新的App上面去。 
原理是在发现代码有更改之后，重新启动应用，但是速度比手动停止后再启动还要更快，更快指的不是节省出来的手工操作的时间。   
其深层原理是使用了两个ClassLoader： 
```$xslt
一个Classloader加载那些不会改变的类（第三方Jar包）;
另一个ClassLoader加载会更改的类，称为  restart ClassLoader
```
  
这样在有代码更改的时候，原来的restart.ClassLoader被丢弃，重新创建一个restart.ClassLoader，由于需要加载的类相比较少，所以实现了较快的重启时间（5秒以内） 


#### 总结
1. devtools会监听classpath下的文件变动，并且会立即重启应用（发生在保存时机），注意：因为其采用的虚拟机机制，该项重启是很快的。  
2. devtools可以实现页面热部署（即页面修改后会立即生效，这个可以直接在application.properties文件中配置spring.thymeleaf.cache=false来实现(这里注意不同的模板配置不一样)  


#### 实现方式
##### 第1步

pom.xml中引用spring-boot-devtools
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <!-- optional=true,依赖不会往下传递，如果有项目依赖本项目，并且想要使用devtools，需要重新引入 -->    <optional>true</optional>
    <scope>true</scope>
</dependency>
```

##### 第2步

如果时eclipse，因为eclipse默认是开启了project->build.Automatically所以devtools会生效
但是Idea默认是不开启，所以还需要手动调开devtools才生效，方式如下：
```$xslt
1）File -> Settings -> Compiler，勾选 Build Project automatically
2）按快捷键Ctrl+Shift+Alt+/，选择1.Registry...
3）勾选 compiler.automake.allow.when.app.running 即可
```


