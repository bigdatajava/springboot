package com.itmayiedu.dao;

import com.itmayiedu.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {

    @Select("SELECT * FROM student WHERE NAME = #{name}")
    Member findByName(@Param("name") String name);
    @Insert("INSERT INTO student(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);



}
