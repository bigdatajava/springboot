### springboot-多数据源


### 易错点

```$xslt
在多数据源的情况下，使用@Transactional注解时，应该指定事务管理者(事务管理者在config配置文件中)
@Transactional(transactionManager = "test2TransactionManager")

```

```$xslt

Springboot1.5的时候 没有默认指向数据源 会报错
    There was an unexpected error (type=Internal Server Error, status=500).
    No qualifying bean of type 'org.springframework.transaction.PlatformTransactionManager' available: expected single matching bean but found 2: test1TransactionManager,test2TransactionManager

Springboot2.0的时候 不报错

```


### 思路

1.分包名（根据不同业务分为不同包）

```
类似多个不同jar，不同包是不同的jar。一些多数据源中间件原理类似，不同包打成不同jar,根据不同包（jar）去使用不同的数据源
```  


2.注释方式（自定义注释，在需要指定数据源的dao上指定数据源）

```$xslt
在项目可以有无限的数据源，具体多少根据内存大小来决定
```

### 设计问题
多数据源会产生 分布式事务 问题。

### question解决
报错
```$xslt
 java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)错误，
```
解决
```$xslt
1.首先看下用户名密码是否正确（有时候用户名localhost会忘记改成使用的IP）
2.看下用户名密码后面是否有多余的空格
```



