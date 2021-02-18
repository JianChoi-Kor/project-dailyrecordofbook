package com.jian.project1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jian.project1.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;

    // 로그인
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 회원가입
	@GetMapping("/join")
	public void join() {
		
	}
	
	@ResponseBody
	@PostMapping("/join")
	public int join(@RequestBody UserEntity p) {
		System.out.println("join input email : " + p.getUserEmail());
		System.out.println("join input pw : " + p.getUserPw());
		
		int joinResult = service.join(p);
		
		return joinResult;
	}
	
	// 약관동의
	@GetMapping("/terms") 
	public void terms() {
		
	}
	
}
