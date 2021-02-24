
// 회원 탈퇴
var withDrawalBtnElem = document.querySelector('#withDrawalBtn')

if(withDrawalBtnElem) {
	var withDrawal_frmElem = document.querySelector('#withDrawal_frm')
	var userPwElem = withDrawal_frmElem.userPw
	
	function ajax() {

		if(userPwElem.value === '') {
			alert('비밀번호를 입력해주세요.')
			return
		}
		
		var param = {
			userPw: userPwElem.value
		}
		
		fetch('/user/withDrawal', {
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
	withDrawalBtnElem.addEventListener('click', ajax)
	
	function proc(result) {
		
		if(confirm('정말로 탈퇴하시겠습니까?')) {
			
			switch (result) {
				case 1:
				alert('회원 탈퇴 되었습니다.')
				location.href='/user/logout'
				return
			
				case 2:
				alert('비밀번호가 틀립니다.')
				return
			}		
		}
	}
}