### 纯手写springboot-tomcat

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
创建servlet类

```
public class IndexServet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    resp.getWriter().print("springboot2.0");
	}
}
                
```
##### 第3步
创建tomcat运行
```$xslt

public class TomcatApp {
        //端口号
        private static int PORT = 8080;
        //项目名称
        private static String CONTEX_PATH = "/itmayiedu";
        //servlet名
        private static String SERVLET_NAME = "testServlet";
 
        public static void main(String[] args) throws LifecycleException, InterruptedException {
    
            // 创建tomcat服务器
            Tomcat tomcatServer = new Tomcat();
            // 指定端口号
            tomcatServer.setPort(PORT);
            // 是否设置自动部署
            tomcatServer.getHost().setAutoDeploy(false);
            // 创建上下文
            StandardContext standardContex = new StandardContext();
            standardContex.setPath(CONTEX_PATH);
            // 监听上下文
            standardContex.addLifecycleListener(new FixContextListener());
            // tomcat容器添加standardContex
            tomcatServer.getHost().addChild(standardContex);
            // 创建Servlet
            tomcatServer.addServlet(CONTEX_PATH, SERVLET_NAME, new TestServlet());
            // 添加servlet url映射
            standardContex.addServletMappingDecoded("/test", SERVLET_NAME);
            tomcatServer.start();
            System.out.println("tomcat服务器启动成功..");
            // 异步进行接收请求
            tomcatServer.getServer().await();
    
        }

}

```
