
// 회원가입
var joinBtnElem = document.querySelector('#joinBtn')

if (joinBtnElem) {
	var join_frmElem = document.querySelector('#join_frm')
	var userEmailElem = join_frmElem.userEmail
	var userPwElem = join_frmElem.userPw
	var userPwReElem = join_frmElem.userPwRe
	var userNmElem = join_frmElem.userNm
	var userPnElem = join_frmElem.userPn
	var searchInfoElem = join_frmElem.searchInfo
	var readingVolumeElem = document.querySelector('#readingVolume')



	// 이메일 유효성 검사 
	function CheckEmail(str) {
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

		if (!reg_email.test(str)) {
			return false;
		}
		else {
			return true;
		}
	}

	// 비밀번호 유효성 검사
	function CheckPassword(uem, upw){
		
		// 이메일 @ 앞부분 자르기
		var uid = uem.substring(0, uem.indexOf('@'))
		
		if (!/^[a-zA-Z0-9]{8,20}$/.test(upw)) {
			alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
			return false;
		}
		var chk_num = upw.search(/[0-9]/g);
		var chk_eng = upw.search(/[a-z]/ig);
		if (chk_num < 0 || chk_eng < 0) {
			alert("비밀번호는 숫자와 영무자를 혼용하여야 합니다.");
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



	function ajax() {
		// userEmail만 변수로 만들어서 사용
		userEmail = userEmailElem.value
		userPw = userPwElem.value

		if (userEmail === '') {
			alert('이메일을 입력해주세요.')
			return
		} else if (!CheckEmail(userEmail)) {
			alert('잘못된 이메일 형식입니다.')
			return
		} else if (userPwElem.value === '') {
			alert('비밀번호를 입력해주세요.')
			return
		} else if (!CheckPassword(userEmail, userPw)) {
			//
			return
		} else if (userNmElem.value === '') {
			alert('이름을 입력해주세요.')
			return
		} else if (userPnElem.value === '') {
			alert('전화번호를 입력해주세요.')
			return
		}

		if (userPwElem.value !== userPwReElem.value) {
			alert('비밀번호가 일치하지 않습니다.')
			return
		}

		var param = {
			userEmail: userEmailElem.value,
			userPw: userPwElem.value,
			userNm: userNmElem.value,
			userPn: userPnElem.value,
			searchInfo: searchInfoElem.value,
			readingVolume: readingVolumeElem.options[readingVolumeElem.selectedIndex].value
		}

		fetch('/user/join', {
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
	joinBtnElem.addEventListener('click', ajax)

	function proc(result) {
		switch (result) {
			case 1:
				alert('회원가입에 성공했습니다.')
				location.href = '/user/login'
				return

			case 0:
					alert('회원가입에 실패했습니다.')
				return

			case 2:
				alert('이미 회원가입된 아이디입니다.')
				return
		}
	}


}




