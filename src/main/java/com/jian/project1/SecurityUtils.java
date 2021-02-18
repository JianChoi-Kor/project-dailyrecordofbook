package com.jian.project1;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
	
	// salt 생성 메서
	public String getSalt() {
		return BCrypt.gensalt();
	}
	
	// password와 salt로 암호화된 password 생성하는 메서드
	public String getHashPw(String password, String salt) {
		return BCrypt.hashpw(password, salt);
	}
}
