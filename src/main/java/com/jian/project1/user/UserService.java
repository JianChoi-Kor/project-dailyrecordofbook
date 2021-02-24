package com.jian.project1.user;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.Const;
import com.jian.project1.FileUtils;
import com.jian.project1.SecurityUtils;
import com.jian.project1.model.UserDTO;
import com.jian.project1.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private FileUtils fUtils;
	
	
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
		loginUser.setUserPn(null);
		loginUser.setRegDt(null);
		loginUser.setSearchInfo(null);
		loginUser.setReadingVolume(null);
		
		
		
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
		System.out.println(p.getAuthKey());
		UserEntity loginUser = mapper.selUser(p);
		
		// 입력된 이메일의 authKey 값을 가지고 온다.
		String loginUserAuthKey = loginUser.getAuthKey();
		
		System.out.println(loginUserAuthKey);
		
		// 입력된 이메일의 authKey 값과 메일 인증에서 받은 authKey 값이 같은지 확인
		if(loginUserAuthKey.equals(p.getAuthKey())) {
			return mapper.updAuthStatus(p); // authKey 확인 완료 => return 1;
		} else {
			return 2; // authKey가 다른 경우
		}
	}
	
	
	// 프로필 업로드 메서드
	public int uploadProfile(MultipartFile mf, HttpSession hs) {
		int userPk = sUtils.getLoginUserPk(hs);
		if(userPk == 0 || mf == null) { // 로그인이 안 되어 있는 경우, 파일이 없는 경우
			return 0;
		}
		
		String folder = "/res/img/user/" + userPk;
		String profileImg = fUtils.transferTo(mf, folder);
		
		if(profileImg == null) { // 파일 생성 실패
			return 0;
		}
		
		UserEntity p = new UserEntity();
		p.setUserPk(userPk);
		
		UserEntity userInfo = mapper.selUser(p);
		if(userInfo.getProfileImg() != null) {
			String basePath = fUtils.getBasePath(folder);
			File file = new File(basePath, userInfo.getProfileImg());
			if(file.exists()) {
				file.delete();
			}
		}
		p.setProfileImg(profileImg);
		return mapper.updUser(p);
	}
	
	public UserEntity selUser(UserEntity p) {
		return mapper.selUser(p);
	}
	
	
	public int changePw(UserDTO p, HttpSession hs) {
		UserEntity loginUser = sUtils.getLoginUser(hs);
		
		// 기존의 비밀번호 + salt
		String loginUserPw = loginUser.getUserPw();
		System.out.println("기존의 비밀번호 : " + loginUserPw);
		String salt = loginUser.getSalt();
		
		// 입력받은 비밀번호
		String inputPw = p.getUserPw();
		// 입력받은 비밀번호로 hashPw 생성
		String inputUserHashPw = sUtils.getHashPw(inputPw, salt);
		
		// 새로운 비밀번호
		String newPw = p.getNewUserPw();
		String newPwRe = p.getNewUserPwRe();
		
		// 기존의 비밀번호가 일치하고 새로운 비밀번호 두개도 일치한다면 사용할 newHashPw
		String newHashPw = sUtils.getHashPw(newPw, salt);
		
		// 기존 비밀번호 확인
		if(inputUserHashPw.equals(loginUserPw)) {
			
			// 기존의 비밀번호와 바꾸려는 비밀번호가 같다면?
			if(loginUserPw.equals(newHashPw)) {
				return 4; // 새로운 비밀번호를 입력해주세요.
			}
			
			// 새로 입력된 비밀번호 두개 비교
			if(newPw.equals(newPwRe)) {
				
				// 새로운 비밀번호를 userPw에 Set
				loginUser.setUserPw(newHashPw);
				// 비밀번호 업데이트 return 1
				return mapper.updUser(loginUser);
			} else {
				return 3; // 새로 입력된 두개의 비밀번호가 다른 경우
			}
		} else {
			return 2; // 기존의 비밀번호가 틀린 경우
		}
	}
	
	
	public int withDrawal(UserEntity p, HttpSession hs) {
		UserEntity loginUser = sUtils.getLoginUser(hs);
		
		// 기존의 비밀번호 + salt
		String loginUserPw = loginUser.getUserPw();
		String salt = loginUser.getSalt();
		
		// 입력받은 비밀번호
		String inputPw = p.getUserPw();
		
		// 입력받은 비밀번호로 hashPw 생성
		String inputUserHashPw = sUtils.getHashPw(inputPw, salt);
		
		// 비밀번호 확인
		if(inputUserHashPw.equals(loginUserPw)) {
			return mapper.delUser(loginUser);
		} else {
			return 2; // 비밀번호가 다릅니다.
		}
	}
	
}





