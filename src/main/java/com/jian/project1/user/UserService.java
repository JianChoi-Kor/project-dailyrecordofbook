package com.jian.project1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.project1.SecurityUtils;
import com.jian.project1.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
	SecurityUtils sUtils;
	
	// email 중복 체크하는 메서드
	public int chkEmail(UserEntity p) {
		UserEntity chkUserEmail = mapper.selUser(p);
		if(chkUserEmail != null) {
			return 1;
		}
		return 0;
	}
	
	public int join(UserEntity p) {
		if(chkEmail(p) == 0) {
			String salt = sUtils.getSalt();
			String hashPw = sUtils.getHashPw(p.getUserPw(), salt);
			
			p.setSalt(salt);
			p.setUserPw(hashPw);
			
			return mapper.insUser(p);
			// 회원가입 성공시 1이 리턴
		} else {
			return 0;
			// 회원가입 실패시 0이 리턴
		}
	}
}