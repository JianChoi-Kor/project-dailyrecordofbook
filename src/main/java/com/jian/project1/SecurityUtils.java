package com.jian.project1;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.jian.project1.model.UserEntity;

@Component
public class SecurityUtils {
	
	// salt 생성 메서드
	public String getSalt() {
		return BCrypt.gensalt();
	}
	
	// password와 salt로 암호화된 password 생성하는 메서드
	public String getHashPw(String password, String salt) {
		return BCrypt.hashpw(password, salt);
	}
	
	public int getLoginUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);	
		return loginUser == null? -1 : loginUser.getUserPk();
	}
	
	public UserEntity getLoginUser(HttpSession hs) {
		return (UserEntity)hs.getAttribute(Const.KEY_LOGINUSER);
	}
}
