package com.itmayiedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.itmayiedu.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM users  ")
	List<User> findUserList();
}
