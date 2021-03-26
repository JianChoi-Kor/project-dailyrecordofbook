package com.jian.project1.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jian.project1.model.LikeDomain;
import com.jian.project1.model.LikeEntity;

@RestController
@RequestMapping("/liked")
public class LikeController {
	
	@Autowired
	private LikeService service;
	
	
	@PostMapping
	public int ins(@RequestBody LikeEntity p) {
		return service.insLike(p);
	}
	
	@GetMapping
	public List<LikeEntity> list(LikeEntity p) {
		return service.selLikeList(p);
	}
	
	@GetMapping("/total")
	public int total(LikeEntity p) {
		return service.total(p);
	}
	
	
	@DeleteMapping
	public int del(LikeEntity p) {
		return service.delLike(p);
	}
	
	
	
}
