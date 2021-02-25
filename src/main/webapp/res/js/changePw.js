
// 비밀번호 변경
var changePwBtnElem = document.querySelector('#changePwBtn')

if(changePwBtnElem) {
	var changePw_frmElem = document.querySelector('#changePw_frm')
	var userPwElem = changePw_frmElem.userPw
	var newUserPwElem = changePw_frmElem.newUserPw
	var newUserPwReElem = changePw_frmElem.newUserPwRe
	
	
	// input type="hidden"에 저장된 값을 가지고 온다.
	// 비밀번호 변경에 유효성 검사에 사용하기 위해서
	var userEmailElem = changePw_frmElem.userEmail
	
	
	
	
	
	function ajax() {
		
		// 버튼이 눌렸을 때 .value로 값을 받아온다.
		// 중요!! .value의 위치가 중요하다.
		userEmail = userEmailElem.value
		newUserPw = newUserPwElem.value
		
		if(userPwElem.value === '') {
			alert('기존 비밀번호를 입력해주세요.')
			return
		} else if(newUserPwElem.value === '') {
			alert('새로운 비밀번호를 입력해주세요.')
			return
		} else if(!CheckPassword(userEmail, newUserPw)) {
			//
			return
		}
		
		if(newUserPwElem.value !== newUserPwReElem.value) {
			alert('새로운 비밀번호가 일치하지 않습니다.')
			return
		}
		
		var param = {
			userPw: userPwElem.value,
			newUserPw: newUserPwElem.value,
			newUserPwRe: newUserPwReElem.value
		}
		
		fetch('/user/changePw', {
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(result) {
			proc(result)
		})
		
	}
	changePwBtnElem.addEventListener('click', ajax)
	
	function proc(result) {
		switch(result) {
			case 1:
			alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.')
			location.href='/user/logout'
			return
			
			case 2:
			alert('비밀번호가 틀립니다.')
			return
			
			case 3:
			alert('새로운 비밀번호가 일치하지 않습니다.')
			return
			
			case 4:
			alert('기존의 비밀번호와 다른 비밀번호를 입력해주세요.')
			return
		}
	}
}



// 비밀번호 유효성 검사
	function CheckPassword(uem, upw){
		
		// 이메일 @ 앞부분 자르기
		var uid = uem.substring(0, uem.indexOf('@'))
		
		if (!/^[a-zA-Z0-9!@#$%^*+=-]{8,20}$/.test(upw)) {
			alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
			return false;
		}
		var chk_num = upw.search(/[0-9]/g);
		var chk_eng = upw.search(/[a-z]/ig);
		if (chk_num < 0 || chk_eng < 0) {
			alert("비밀번호는 숫자와 영문자를 혼용하여야 합니다.");
			return false;
		}
		if (/(\w)\1\1\1/.test(upw)) {
			alert("비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.");
			return false;
		}
		if (upw.search(uid) > -1) {
			alert("ID가 포함된 비밀번호는 사용하실 수 없습니다.");
			return false;
		}
		return true;
	}