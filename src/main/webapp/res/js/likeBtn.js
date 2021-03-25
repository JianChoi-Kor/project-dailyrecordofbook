/**
 * 
 */

var pageInfo = document.querySelector('#pageInfo')

if(pageInfo) {
	console.log('category'+ pageInfo.dataset.category)
	console.log('boardpk'+ pageInfo.dataset.boardpk)
	console.log('loginuserpk'+ pageInfo.dataset.loginuserpk)
}



$(document).ready(likeLoad())

function likeLoad() {
	
	var cmtBoardPk = pageInfo.dataset.boardpk
	var userPk = pageInfo.dataset.loginuserpk
	
	if(userPk.isNaN) {
		userPk = 0
	}
	
	fetch(`/liked?cmtBoardPk=${cmtBoardPk}&userPk=${userPk}`)
		.then(res => res.json())
		.then(result => {
			console.log(result)
			chkLike(result)
		})

}


function chkLike(result) {
	
	if(result.length === 0) {
		return 
	}
	result.forEach(function(item) {

		var cmtId = document.querySelector(`#heart-icon${item.cmtSeq}`)
		console.log(cmtId)
		
		const PRESSED_CLASS = "press"
		cmtId.classList.add(PRESSED_CLASS)
		
	})
}






function liked(cmtSeq, loginUserPk) {
	
	console.log('loginuserpk'+ loginUserPk)
	console.log('cmtSeq'+ cmtSeq)
	
	if(isNaN(loginUserPk)) {
		if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')) {
			location.href = '/user/login'
			return
		}
	} else {

		// 동작 모션
		likeMotion(cmtSeq)
		
		var cmtId = document.querySelector(`#heart-icon${cmtSeq}`)
		
		if(cmtId.classList.contains('press')) {
		
			// DB에 좋아요 추가
			likeAddAjax(cmtSeq, loginUserPk)
		}
		
		else {
			// DB에 좋아요 삭제
			likeDelAjax(cmtSeq, loginUserPk)
		}
		
		
		
	}

	
}


// DB에 좋아요 저장
function likeAddAjax(cmtSeq, loginUserPk) {
	
	var param = {
		cmtBoardPk: pageInfo.dataset.boardpk,
		cmtSeq: cmtSeq,
		userPk: loginUserPk
	}
	
	fetch('/liked', {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}).then(function(res) {
		return res.json()
	}).then(function(result) {
		if(result === 1) {
			console.log('좋아요 저장')
		} else if(result === 0) {
			console.log('좋아요 삭제')
		} else {
			console.log('다른 오류')
		}
	})
	
}

// DB에 좋아요 삭제
function likeDelAjax(cmtSeq, loginUserPk) {
	
	var cmtBoardPk = pageInfo.dataset.boardpk
	var	userPk = pageInfo.dataset.loginuserpk
	
	fetch(`/liked?cmtBoardPk=${cmtBoardPk}&cmtSeq=${cmtSeq}&userPk=${userPk}`, {
		method: 'delete'
	}).then(res => res.json())
	.then(result => {
		if(result === 1) {
			console.log('좋아요 취소')
		}
	})
}






function likeMotion(cmtSeq) {
	
	const iconElem = document.querySelector(`#heart-icon${cmtSeq}`)
	const spanElem = document.querySelector(`#liked-span${cmtSeq}`)
	
	const PRESSED_CLASS = "press"
		
  	iconElem.classList.toggle(PRESSED_CLASS)
 	spanElem.classList.toggle(PRESSED_CLASS)
}








