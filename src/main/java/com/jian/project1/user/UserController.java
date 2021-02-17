package com.jian.project1.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // 로그인
	@GetMapping("login")
	public void login() {
		
	}
	
	// 회원가입
	@GetMapping("join")
	public void join() {
		
	}
	
	// 약관동의
	@GetMapping("/terms") 
	public void terms() {
		
	}
	
}
