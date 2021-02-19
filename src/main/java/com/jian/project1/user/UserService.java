package com.jian.project1.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.project1.Const;
import com.jian.project1.SecurityUtils;
import com.jian.project1.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private SecurityUtils sUtils;
	
	
	// email 중복 체크하는 메서드
	public int chkEmail(UserEntity p) {
		UserEntity chkUserEmail = mapper.selUser(p);
		if(chkUserEmail != null) {
			return 1; // 중복된 아이디가 있는 경우
		}
		return 0; // 중복된 아이디가 없는 경우
	}
	
	
	// 회원가입 메서드
	public int join(UserEntity p) {
		if(chkEmail(p) == 0) {
			String salt = sUtils.getSalt();
			String hashPw = sUtils.getHashPw(p.getUserPw(), salt);
			
			p.setSalt(salt);
			p.setUserPw(hashPw);
			
			return mapper.insUser(p); // 회원가입 성공
		} else {
			return 2; // 회원가입 실패
		}
	}

	
	// 로그인 메서드
	public int login(UserEntity p, HttpSession hs) {
		UserEntity loginUser = mapper.selUser(p);
		if(loginUser == null) {
			return 2; // 존재하지 않는 이메일입니다.
		}
		
		// 회원가입 된 이메일이라면 DB에 저장된 salt 값을 가지고 옴
		String salt = loginUser.getSalt();
		// 입력된 비밀번호와 저장된 salt를 통해 hashPw를 생성
		String loginUserHashPw = sUtils.getHashPw(p.getUserPw(), salt);
		// 입력된 비밀번호와 저장된 비밀번호를 비교
		if(!loginUserHashPw.equals(loginUser.getUserPw())) {
			return 3;
		}
		
		// 이메일 인증 확인 부분 authStatus 값이 1이 아니라면
		if(loginUser.getAuthStatus() != 1) {
			return 4;
		}
		
		
		// 메모리에 올려둘 필요가 없는 값들을 지움
		loginUser.setUserPw(null);
		loginUser.setSalt(null);
		loginUser.setRegDt(null);
		
		// 실수를 막기 위해서 Const 클래스를 만들고 키 값을 변수로 만들어서 사용
		hs.setAttribute(Const.KEY_LOGINUSER, loginUser);
		
		return 1; // 로그인 성공
	}
	
	
	// 회원가입 성공시 authKey 업데이트하는 메서드
	public int updAuthKey(UserEntity p) {
		return mapper.updAuthKey(p);
	}
	
	
	// 인증메일 클릭시 authStatus 업데이트하는 메서드
	public int chkAndUpdAuthStatus(UserEntity p) {
		UserEntity loginUser = mapper.selUser(p);
		
		// 입력된 이메일의 authKey 값을 가지고 온다.
		String loginUserAuthKey = loginUser.getAuthKey();
		
		// 입력된 이메일의 authKey 값과 메일 인증에서 받은 authKey 값이 같은지 확인
		if(loginUserAuthKey.equals(p.getAuthKey())) {
			return mapper.updAuthStatus(p); // authKey 확인 완료 => return 1;
		} else {
			return 2; // authKey가 다른 경우
		}
	}
}





