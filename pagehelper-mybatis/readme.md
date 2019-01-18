### springboot-pageHelper-mybatis

#### 说明

三种常见数据库分页查询方法：   
| mysql-limit | oraclet-伪列 | sqlserver-top |
```$xslt
pageHelper:底层实现原理采用改写语句,帮我们生成语句
```

#### 使用说明
##### 1.导入maven依赖
```$xslt
<!-- springboot 整合 pagehelper -->
		<dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>1.2.5</version>
		</dependency>
		<dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
		</dependency>
导入对应的JDBC依赖（例如MySQL）
<dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.1.1</version>
		</dependency>
		<!-- mysql 依赖 -->
		<dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
		</dependency>
```
##### 2.在代码中使用pageHelper,示例
```$xslt
	public PageInfo<User> findUserList(int page, int pageSize) {
		// mysql 查询 limit oraclet 伪列 sqlserver top
		
		// ***pageHelper 帮我们生成分页语句
		PageHelper.startPage(page, pageSize); // 底层实现原理采用改写语句

		List<User> listUser = userMapper.findUserList();

		//	PageInfo<User> pageInfoUserList= userMapper.findUserList();
		
		// ***返回给客户端展示
		PageInfo<User> pageInfoUserList = new PageInfo<User>(listUser);
		// 实现原理
		return pageInfoUserList;
	}
```