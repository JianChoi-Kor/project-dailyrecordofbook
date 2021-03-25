package com.jian.project1.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.project1.model.LikeEntity;

@Service
public class LikeService {

	
	@Autowired
	private LikeMapper mapper;
	
	public int insLike(LikeEntity p) {
		return mapper.insLike(p);
	}
	
	
	public List<LikeEntity> selLikeList(LikeEntity p) {
		return mapper.selLikeList(p);
	}
	
	
	public int delLike(LikeEntity p) {
		return mapper.delLike(p);
	}
}
