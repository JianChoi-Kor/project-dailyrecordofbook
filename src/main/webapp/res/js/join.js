
// 회원가입
var joinBtnElem = document.querySelector('#joinBtn')

if(joinBtnElem) {
	var join_frmElem = document.querySelector('#join_frm')
	var userEmailElem = join_frmElem.userEmail
	var userPwElem = join_frmElem.userPw
	var userPwReElem = join_frmElem.userPwRe
	var userNmElem = join_frmElem.userNm
	var userPnElem = join_frmElem.userPn
	var searchInfoElem = join_frmElem.searchInfo
	var readingVolumeElem = document.querySelector('#readingVolume')
	
	function ajax() {
		//TODO 이메일 입력시 @ 등 체크해야할 것들 늘리기
		if(userEmailElem.value === '') {
			alert('이메일을 입력해주세요.')
			return
		} else if(userPwElem.value === '') {
			alert('비밀번호를 입력해주세요.')
			return
		} else if(userNmElem.value === '') {
			alert('이름을 입력해주세요.')
			return
		} else if(userPnElem.value === '') {
			alert('전화번호를 입력해주세요.')
			return
		}
		
		if(userPwElem.value !== userPwReElem.value) {
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
				'Content-Type':'application/json'
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
		switch(result) {
			case 1:
			alert('회원가입에 성공했습니다.')
			location.href='/user/login'
			return
			
			case 0:
			alert('회원가입에 실패했습니다.')
			return
		}
	}	
}







