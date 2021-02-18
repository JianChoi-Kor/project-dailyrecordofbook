package com.jian.project1.user;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity selUser(UserEntity p);
	int insUser(UserEntity p);
}
