package com.jian.project1.cmt;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.CmtEntity;

@Mapper
public interface CmtMapper {
	
	int insCmt(CmtEntity p);

}
