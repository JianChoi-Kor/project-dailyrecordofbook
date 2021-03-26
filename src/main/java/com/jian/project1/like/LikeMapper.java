package com.jian.project1.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.LikeDomain;
import com.jian.project1.model.LikeEntity;

@Mapper
public interface LikeMapper {

	int insLike(LikeEntity p); 
	List<LikeEntity> selLikeList(LikeEntity p);
	int delLike(LikeEntity p);
	int total(LikeEntity p);
}
