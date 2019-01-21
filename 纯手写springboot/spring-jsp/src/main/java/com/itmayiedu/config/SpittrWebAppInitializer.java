package com.itmayiedu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 加载SpringMVCDispatcherServlet
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 加载根容器
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {
				RootConfig.class
		};
	}

	// 加载SpringMVC容器
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] {
				WebConfig.class
		};
	}

	// SpringMVCDispatcherServlet 拦截的请求 /
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}




}
