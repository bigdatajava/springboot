package com.itmayiedu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringMVC容器<br>
 * 
 * @EnableWebMvc 开启SpringMVC功能<br>
 * @Configuration 配置<br>
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.itmayiedu.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

}
