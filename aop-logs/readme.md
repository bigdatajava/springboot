### springboot集合全局日志处理


## 注释说明
实现AOP的切面主要有以下几个要素：

。使用@Aspect注解将一个java类定义为切面类

。使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。根据需要在切入点不同位置的切入内容
##### @Pointcut("execution(public * com.kfit.*.web..*.*(..))")

```
       * ~第一个 *代表任意修饰符及任意返回值.

        * ~第二个 *任意包名

        * ~第三个 *代表任意方法.

        * ~第四个 *定义在web包或者子包

        * ~第五个 *任意方法

        * ~ ..匹配任意数量的参数.

```


。使用@Before在切入点开始处切入内容

。使用@After在切入点结尾处切入内容

。使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）

。使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容

。使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑````。


```
@Aspect
@Component
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	@Pointcut("execution(public * com.itmayiedu.controller.*.*(..))")
	public void webLog() {
	}

	/**
	 * 使用AOP前置通知拦截请求参数信息<br>
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容 记录 最多半年数据迁移 云备份 nosql 数据库
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("name:{},value:{}", name, request.getParameter(name));
		}
		// 传统写在磁盘上有很大缺点： 分布式情况 服务器集群呢？ 20台服务器，
	}

	/**
	 * 后置通知 
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
	}
}
```