
// 로그인
var loginBtnElem = document.querySelector('#loginBtn')

if(loginBtnElem) {
	var login_frmElem = document.querySelector('#login_frm')
	var userEmailElem = login_frmElem.userEmail
	var userPwElem = login_frmElem.userPw
	var errMsgElem = document.querySelector('#errMsg')
	
	function ajax() {
		if(userEmailElem.value === '') {
			alert('아이디를 입력해주세요.')
			return
		} else if(userPwElem.value === '') {
			alert('비밀번호를 입력해주세요.')
			return
		}
		
		var param = {
			userEmail: userEmailElem.value,
			userPw: userPwElem.value
		}
		
		fetch('/user/login', {
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
	loginBtnElem.addEventListener('click', ajax)
	
	function proc(result) {
		switch(result) {
			case 1:
			alert('로그인에 성공했습니다.')
			location.href = '/main/home'
			return
			
			case 2:
			errMsgElem.innerText = '존재하지 않는 아이디입니다.'
			return
			
			case 3:
			errMsgElem.innerText = '비밀번호가 틀립니다.'
			return
		}
	}
}





