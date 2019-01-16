### springboot-async异步使用


## 注释说明
实现异步-主要有以下几个要素：

。启动加上 @EnableAsync ,需要执行异步方法上加入 @Async

。在方法上加上@Async之后 底层使用多线程技术
