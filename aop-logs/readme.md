### springboot集合全局日志处理

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