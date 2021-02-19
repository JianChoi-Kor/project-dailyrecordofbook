package com.jian.project1.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jian.project1.mail.MailSendService;
import com.jian.project1.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MailSendService mailSender;
	
    // 로그인
	@GetMapping("/login")
	public void login() {
		
	}
	
	@ResponseBody
	@PostMapping("/login")
	public int login(@RequestBody UserEntity p, HttpSession hs) {
		System.out.println("inputUserEmail : " + p.getUserEmail());
		System.out.println("inputUserPw : " + p.getUserPw());
		
		int loginResult = service.login(p, hs);
		
		return loginResult;
	}

	
	// 회원가입
	@GetMapping("/join")
	public void join() {
		
	}
	
	@ResponseBody
	@PostMapping("/join")
	public int join(@RequestBody UserEntity p) {
		System.out.println("joinUserEmail : " + p.getUserEmail());
		
		int joinResult = service.join(p);
		System.out.println(joinResult);
		
		if(joinResult == 1) {
			// 회원가입에 성공했다면 임의의 autoKey 생성 => 이메일 발송
			String authKey = mailSender.sendAuthMail(p.getUserEmail());
			p.setAuthKey(authKey);
			
			Map<String, String> returnValue = new HashMap<String, String>();
			returnValue.put("email", p.getUserEmail());
			returnValue.put("authKey", p.getAuthKey());
			System.out.println("result : " + returnValue);
			
			//service.updAuthKey(returnValue);
		}
		
		return joinResult;
	}
	
	// 약관동의
	@GetMapping("/terms") 
	public void terms() {
		
	}
	
}
