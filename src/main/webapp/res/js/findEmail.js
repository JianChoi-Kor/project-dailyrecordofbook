
// 아이디 찾기
var findEmailBtnElem = document.querySelector('#findEmailBtn')
var findEmail_frmElem = document.querySelector('#findEmail_frm')
var userNmElem = findEmail_frmElem.userNm
var userPnElem = findEmail_frmElem.userPn
var errMsgElem = document.querySelector('#errMsg')

if(findEmailBtnElem) {
	
	function ajax() {
		if(userNmElem.value === '') {
			errMsgElem.innerText = '이름을 입력해주세요.'
			return
		} else if (userPnElem.value === '') {
			errMsgElem.innerText = '전화번호를 입력해주세요.'
			return
		}
		
		var param = {
			userNm: userNmElem.value,
			userPn: userPnElem.value
		}
		
		fetch('/user/findEmail', {
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
	findEmailBtnElem.addEventListener('click', ajax)
	
	function proc(result) {
		if(result) {
			alert(`회원가입시 사용한 이메일은 '${result.userEmail}' 입니다.`)
			return
		}
	}
}

