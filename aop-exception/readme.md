## springboot整合AOP异常处理

### 主要使用到了一下几个注解
```
@ControllerAdvice
public class AopException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String aopException(){

        return "使用到了异常处理AOP方法~";

    }
```
