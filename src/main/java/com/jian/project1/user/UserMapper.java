package com.jian.project1.user;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity selUser(UserEntity p);
	int insUser(UserEntity p);
	int updAuthKey(UserEntity p);
	int updAuthStatus(UserEntity p);
	int updUser(UserEntity p);
	int delUser(UserEntity p);
	UserEntity selUserEmail(UserEntity p);
	UserEntity selUserPw(UserEntity p);
}
