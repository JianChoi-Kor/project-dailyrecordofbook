package com.jian.project1.cmt;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.CmtDomain;
import com.jian.project1.model.CmtEntity;

@Mapper
public interface CmtMapper {
	
	int insCmt(CmtEntity p);
	List<CmtDomain> selCmtList(CmtEntity p);
	int updCmt(CmtEntity p);

}
