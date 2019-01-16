## springboot整合AOP异常处理

### 主要使用到了一下几个注解



@ExceptionHandler 表示拦截异常

@ControllerAdvice 是 controller 的一个辅助类，最常用的就是作为全局异常处理的切面类

@ControllerAdvice 可以指定扫描范围

@ControllerAdvice 约定了几种可行的返回值，如果是直接返回 model 类的话，需要使用 @ResponseBody 进行 json 转换

o	返回 String，表示跳到某个 view

o	返回 modelAndView

o	返回 model + @ResponseBody


### 主要代码如下
```
@ControllerAdvice
public class AopException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String aopException(){

        return "使用到了异常处理AOP方法~";

    }
```
