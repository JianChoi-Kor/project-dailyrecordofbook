
// 비밀번호 찾기
var findPwBtnElem = document.querySelector('#findPwBtn')
var findPw_frmElem = document.querySelector('#findPw_frm')
var userEmailElem = findPw_frmElem.userEmail
var userNmElem = findPw_frmElem.userNm
var userPnElem = findPw_frmElem.userPn
var errMsgElem = document.querySelector('#errMsg')

if(findPwBtnElem) {
	
	function ajax() {
		if(userEmailElem.value === '') {
			errMsgElem.innerText = '이메일을 입력해주세요.'
			return
		} else if(userNmElem.value === '') {
			errMsgElem.innerText = '이름을 입력해주세요.'
			return
		} else if (userPnElem.value === '') {
			errMsgElem.innerText = '전화번호를 입력해주세요.'
			return
		}
		
		var param = {
			userEmail: userEmailElem.value,
			userNm: userNmElem.value,
			userPn: userPnElem.value
		}
		
		fetch('/user/findPw', {
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
	findPwBtnElem.addEventListener('click', ajax)
	
	function proc(result) {
		switch (result) {
			case 1:
				alert('임시 비밀번호가 메일로 전송되었습니다.')
				location.href = '/user/login'
				return
			
			case 2:
				alert('입력한 회원 정보가 틀립니다.')
				return
			
			default:
				alert('오류가 발생하였습니다.')
				return
		}
	}
}

