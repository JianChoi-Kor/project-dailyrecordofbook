
// 비밀번호 변경
var changePwBtnElem = document.querySelector('#changePwBtn')

if(changePwBtnElem) {
	var changePw_frmElem = document.querySelector('#changePw_frm')
	var userPwElem = changePw_frmElem.userPw
	var newUserPwElem = changePw_frmElem.newUserPw
	var newUserPwReElem = changePw_frmElem.newUserPwRe
	
	function ajax() {
		if(userPwElem.value === '') {
			alert('기존 비밀번호를 입력해주세요.')
			return
		} else if(newUserPwElem.value === '') {
			alert('새로운 비밀번호를 입력해주세요.')
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
			console.log(result)
		})
		
	}
	changePwBtnElem.addEventListener('click', ajax)
	
	
}