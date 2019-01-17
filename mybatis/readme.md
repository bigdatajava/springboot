### springboot-mybatis


## 有两种方式实现
#### 第一种
```$xslt
@Mapper：将此注释放到需要注入的dao层的类上

    例如
    @Mapper
    public interface MemberDao {
    
        @Select("SELECT * FROM student WHERE NAME = #{name}")
        Member findByName(@Param("name") String name);
        @Insert("INSERT INTO student(name, age) VALUES(#{name}, #{age})")
        int insert(@Param("name") String name, @Param("age") Integer age);

    }
```

#### 第二种
```$xslt
@MapperScan("要扫描的dao层包")：将此注释放到启动类上

    例如
    @MapperScan("com.itmayiedu.dao")
    @SpringBootApplication
    public class MybatisApp {
    
    	public static void main(String[] args) {
    		SpringApplication.run(MybatisApp.class, args);
    	}
    
    }

```

