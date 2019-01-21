### 纯手写springboot-springMVC

#### 说明
```
1.springboot是一个快速整合第三方框架，简化XML配置完全采用注解化，内置HTTP服务器（Jetty和Tomcat等），最终是以Java应用程序进行执行。
2.纯手写springboot基于SpringMVC无配置文件（纯Java）完全注解化+内置tomcat-embed-core实现SpringBoot框架，Main函数启动。
    a)SpringBoot核心快速整合第三方框架原理:Maven继承依赖关系
    b)SpringBoot内嵌入tomcat-embed-core
    c)SpringBoot采用SpringMVC注解版本实现无配置效果

注意:本节说的不是SpringBoot流程而是SpringBoot核心原理

```
springboot思考
```$xslt
1.快速整合第三方框架原理：maven子父依赖关系，相当于需要整合的环境的jar封装好依赖信息
2.完全无配置文件（采用注解化）原理：
    1)如何初始化？没有web.xml，那么tomcat如何启动呢？
    注解在什么时候产生？spring3.0以上提供注解
    springMVC内置注解加载整个springMVC容器
    使用Java代码编写springMVC配置初始化
    传统web项目，通过什么配置文件加载整个项目流程-web.xml-class文件
3.内置HTTP服务器
    1）Java语言创建Tomcat容器，加载class文件
    
```

springMVC原理执行流程
![](https://github.com/NOHELLO/picture/raw/master/springMVC.png)
```$xslt
⑴ 用户发送请求至前端控制器DispatcherServlet
⑵ DispatcherServlet收到请求调用HandlerMapping处理器映射器。
⑶ 处理器映射器根据请求url找到具体的处理器，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。
⑷ DispatcherServlet通过HandlerAdapter处理器适配器调用处理器
⑸ 执行处理器(Controller，也叫后端控制器)。
⑹ Controller执行完成返回ModelAndView
⑺ HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet
⑻ DispatcherServlet将ModelAndView传给ViewReslover视图解析器
⑼ ViewReslover解析后返回具体View
⑽ DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）。
⑾ DispatcherServlet响应用户。

```
springMVC注解启动方式
```$xslt
DispatcherServlet是Spring MVC的核心，每当应用接受一个HTTP请求，由DispatcherServlet负责将请求分发给应用的其他组件。
在旧版本中，DispatcherServlet之类的servlet一般在web.xml文件中配置，该文件一般会打包进最后的war包种；
Spring 3引入了注解

```




#### 实现方式
##### 第1步

pom.xml中引用maven依赖
```$xslt
<dependencies>
		<!--Java语言操作tomcat -->
		<dependency>
                <groupId>org.apache.tomcat.embed</groupId>
                <artifactId>tomcat-embed-core</artifactId>
                <version>8.5.16</version>
		</dependency>
		<!-- spring-web -->
		<dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-web</artifactId>
                <version>5.0.4.RELEASE</version>
                <scope>compile</scope>
		</dependency>
		<!-- spring-mvc -->
		<dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>5.0.4.RELEASE</version>
                <scope>compile</scope>
		</dependency>
		<!-- tomcat对jsp支持 -->
		<dependency>
                <groupId>org.apache.tomcat</groupId>
                <artifactId>tomcat-jasper</artifactId>
                <version>8.5.16</version>
		</dependency>
	</dependencies>


```

##### 第2步
加载SpringMVCDispatcherServlet
```$xslt
a) AbstractAnnotationConfigDispatcherServletInitializer这个类负责配置DispatcherServlet、初始化Spring MVC容器和Spring容器。
b) getRootConfigClasses()方法用于获取Spring应用容器的配置文件，这里我们给定预先定义的RootConfig.class；
c) getServletConfigClasses负责获取Spring MVC应用容器，这里传入预先定义好的WebConfig.class；
d) getServletMappings()方法负责指定需要由DispatcherServlet映射的路径，这里给定的是"/"，意思是由DispatcherServlet处理所有向该应用发起的请求。
代码：

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 加载根容器
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class };
	}

	// 加载SpringMVC容器
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebConfig.class };
	}

	// SpringMVCDispatcherServlet 拦截的请求 /
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

}


```
##### 第3步
加载SpringMVC容器    
```$xslt
正如可以通过多种方式配置DispatcherServlet一样，也可以通过多种方式启动Spring MVC特性。原来我们一般在xml文件中使用<mvc:annotation-driven>元素启动注解驱动的Spring MVC特性。
代码：

@Configuration
@EnableWebMvc
@ComponentScan("com.itmayiedu.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

}

```

##### 第4步
根容器RootConfig
```$xslt

@Configuration
@ComponentScan(basePackages = "com.itmayiedu")
public class RootConfig {

}

```  

##### 第5步
main启动类代码
```$xslt
	public static void main(String[] args) throws ServletException, LifecycleException {
	    	start();
	}

	public static void start() throws ServletException, LifecycleException {

            // 创建Tomcat容器
            Tomcat tomcatServer = new Tomcat();
            // 端口号设置
            tomcatServer.setPort(9090);
            // 读取项目路径
            //这里如果报错读取路径找不到，那就改用绝对路径
            StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
            // 禁止重新载入
            ctx.setReloadable(false);
            // class文件读取地址
            File additionWebInfClasses = new File("target/classes");
            // 创建WebRoot
            WebResourceRoot resources = new StandardRoot(ctx);
            // tomcat内部读取Class执行
            resources.addPreResources(
                    new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            tomcatServer.start();
            // 异步等待请求执行
            tomcatServer.getServer().await();

	}

```

##### 第6步
添加业务逻辑层  
###### 1）添加server层
```$xslt
@Service
public class UserService {

        public String index() {
            return "这是我的第一个UserService,哈哈哈";
        }

}

```

###### 2) 添加controller
```$xslt
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
    public String index() {
        return userService.index();
    }

}

```

总结：
```$xslt
读取项目路径:
    StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
如果报错读取路径找不到，那就改用绝对路径:
    StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("D:\\IDEA2017space\\mayi\\纯手写springboot\\spring-mvc\\src\\main").getAbsolutePath());
```