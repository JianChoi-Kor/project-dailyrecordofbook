/**
 * 
 */

var pageInfo = document.querySelector('#pageInfo')

// 글 삭제 버튼 ajax
var del_btnElem = document.querySelector('#del_btn')

if (del_btnElem) {
	del_btnElem.addEventListener('click', function() {
		if (confirm('삭제하시겠습니까?')) {
			ajax()
		}
	})

	var category = pageInfo.dataset.category

	function ajax() {
		var boardpk = pageInfo.dataset.boardpk

		fetch(`/board/del/${boardpk}`, {
			method: 'delete'
		}).then(function(res) {
			return res.text()
		}).then(function(result) {
			delProc(result)
		})
	}

	function delProc(result) {
		if (result == 1) {

			if (category > 10) {
				alert('글이 삭제되었습니다.')
				location.href = `/board/community?category=${category}`
				return
			} else {
				alert('글이 삭제되었습니다.')
				location.href = `/board/list?category=${category}`
				return
			}

			alert('글이 삭제되었습니다.')
			location.href = `/board/list?category=${category}`
			return
		} else {
			alert('글을 삭제하지 못했습니다.')
			return
		}
	}
}


// 모집 마감 변경 버튼 ajax
var close_btnElem = document.querySelector('#close_btn')

if (close_btnElem) {
	close_btnElem.addEventListener('click', function() {
		if (confirm('모집 상태를 모집마감으로 변경하시겠습니까?'))
			ajax()
	})

	var category = pageInfo.dataset.category


	function ajax() {
		var boardpk = pageInfo.dataset.boardpk

		fetch(`/board/close/${boardpk}`, {
			method: 'put'
		}).then(function(res) {
			return res.text()
		}).then(function(result) {
			closeProc(result)
		})
	}

	function closeProc(result) {
		if (result == 1) {
			alert('모집 마감으로 변경되었습니다.')
			location.href = `/board/community?category=${category}`
			return
		} else {
			alert('상태 변경에 실패했습니다.')
			return
		}
	}
}




function chkLogin(loginUserPk) {
	
	if(loginUserPk == '' || loginUserPk == null) {
		
		if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')) {
			location.href = '/user/login'
			return
		}

	}
}

























// -----------------------  댓글 부분  ------------------------ //




// 댓글 작성 부분
var cmtFrmElem = document.querySelector('#cmtFrm')

if (cmtFrmElem) {

	var cmtContentElem = cmtFrmElem.cmtContent
	var cmtWriteBtnElem = document.querySelector('#cmtWriteBtn')

	function ajax() {

		var cmtContentVal = cmtContentElem.value
		if (cmtContentVal === '') {
			alert('댓글 내용을 작성해주세요.')
			return
		}

		var param = {
			cmtBoardPk: pageInfo.dataset.cmtboardpk,
			cmtContent: cmtContentElem.value
		}

		fetch('/cmt', {
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(result) {
			if (result === 1) {
				console.log('성공')
				cmtContentElem.value = ''
				selCmtList()
			}
			else {
				alert('댓글 쓰기에 실패했습니다.')
			}
		})
	}

	cmtWriteBtnElem.addEventListener('click', ajax)
}





// 댓글 리스트 출력 부분
var cmtListElem = document.querySelector('#cmtList')


function selCmtList() {

	
	fetch(`/cmt?cmtBoardPk=${pageInfo.dataset.cmtboardpk}`)
		.then(res => res.json())
		.then(result => {
			clearView()
			createView(result)
		})

	// 댓글창 초기화
	function clearView() {
		cmtListElem.innerHTML = ''
	}

	// 댓글 리스트 출력
	function createView(result) {
		if (result.length === 0) {
			return
		}
		result.forEach(function(item) {
			var contentDivElem = document.createElement("div")
			contentDivElem.innerHTML = createRecord(item)
			cmtListElem.append(contentDivElem)
		})

	}

	// 댓글 하나씩 만드는 함수
	function createRecord(item) {
		
		if(item.cmtIsDel == 1) {
			item.cmtContent = '삭제된 댓글입니다.'
		}
		
		var html = ''
		
		
		html += '<div class="oneMod hidden" id="oneMod'+item.cmtSeq+'">'
		html +=	addUpdFrm(item) 
		html += '</div>'


		// 본인이 쓴 댓글인지 확인하기 위한 loginUserPk + likde 기능
		var loginUserPk = parseInt(pageInfo.dataset.loginuserpk)


		html += '<div class="oneCmt" id="oneCmt'+item.cmtSeq+'">'
		html += 	"<div>"
		html += 		'<img class="cmtImg" src="/res/img/user/'+item.writerPk+'/'+item.writerProfileImg+'"'
		html += 		">"
		html += 	"</div>"

		html += 	'<div class="cmt_right">'
		html += 		'<div class="cmt_top_line">'
		html +=				"<span>"+item.writerNm+"</span>"
		html +=				"&nbsp&nbsp"
		html +=				'<span>'+item.cmtRegDt+'&nbsp&nbsp&nbsp&nbsp&nbsp</span>'
		html +=				'<div class="likeForm">'
		html +=					'<i class="fas fa-heart heart-icon" id="heart-icon'+item.cmtSeq+'" onclick="liked('+item.cmtSeq+' ,'+loginUserPk+')"></i>'
		html +=					'<span class="liked-span" id="liked-span'+item.cmtSeq+'">liked!</span>'
		
		html +=					'<span class="likeCount" id="count'+item.cmtSeq+'">좋아요 </span>'
		
		html +=				"</div>"
		html +=			"</div>"
	
		html +=			"<div>"
		html +=				'<p class="oneContent">'+item.cmtContent+'</p>'
		html += 		"</div>"










		/*

		html += '<div class="oneCmt" id="oneCmt'+item.cmtSeq+'">'
		html += 	"<div>"
		html += 		'<img class="cmtImg" src="/res/img/user/'+item.writerPk+'/'+item.writerProfileImg+'"'
		html += 		">"
		html += 	"</div>"

		html += 	'<div class="cmt_right">'
		html += 		"<div>"
		html +=				"<span>"+item.writerNm+"</span>"
		html +=				"&nbsp&nbsp"
		html +=				"<span>"+item.cmtRegDt+"</span>"
		html +=			"</div>"
		html +=			"<div>"
		html +=				'<p class="oneContent">'+item.cmtContent+'</p>'
		html += 		"</div>"
		
		*/
		
		// 자신이 쓴 댓글이라면 삭제, 수정버튼 추가
		console.log(loginUserPk)
		if(loginUserPk === item.writerPk && item.cmtIsDel !== 1) {
	
					html +=			"<div>"
					html +=				'<input type="button" class="cmt_btn" id="mod_btn'+item.cmtSeq+'" value="수정" onclick="makeModFrm('+item.cmtSeq+')">'
					html +=				"&nbsp"
					html +=				'<input type="button" class="cmt_btn" id="del_btn'+item.cmtSeq+'" value="삭제" onclick="delAjax('+item.cmtBoardPk+', '+item.cmtSeq+')">'                      
					html +=			"</div>"
		}
		
		html +=		"</div>"
		html +=	"</div>"
		
		return html
	
	}



}

selCmtList()



	// 댓글 수정 창을 띄우는 부분
	function makeModFrm(cmtSeq) {
		
		var cmtUpdId1 = 'oneMod' + cmtSeq
		console.log(cmtUpdId1)
		var newUpdFrm = document.getElementById(`${cmtUpdId1}`)
		newUpdFrm.classList.remove('hidden')
		
		
		var cmtUpdId2 = 'oneCmt' + cmtSeq
		console.log(cmtUpdId2)
		var oriUpdFrm = document.getElementById(`${cmtUpdId2}`)
		oriUpdFrm.classList.add('hidden')

	}
	
	
	// 댓글 수정 취소 부분
	function canModFrm(cmtSeq) {
		
		var cmtUpdId1 = 'oneMod' + cmtSeq
		console.log(cmtUpdId1)
		var newUpdFrm = document.getElementById(`${cmtUpdId1}`)
		newUpdFrm.classList.add('hidden')
		
		
		var cmtUpdId2 = 'oneCmt' + cmtSeq
		console.log(cmtUpdId2)
		var oriUpdFrm = document.getElementById(`${cmtUpdId2}`)
		oriUpdFrm.classList.remove('hidden')

	}
	
	
	


// 새로 열리는 댓글 수정 Form
function addUpdFrm(item) {
	var addHtml = ''
	
	addHtml += 	"<div>"
	addHtml += 		'<img class="cmtImg" src="/res/img/user/'+item.writerPk+'/'+item.writerProfileImg+'"'
	addHtml += 		">"
	addHtml += 	"</div>"

	addHtml += 	'<div class="cmt_right">'
	addHtml += 		"<div>"
	addHtml +=				"<span>"+item.writerNm+"</span>"
	addHtml +=				"&nbsp&nbsp"
	addHtml +=				"<span>"+item.cmtRegDt+"</span>"
	addHtml +=			"</div>"
	addHtml +=			"<div>"
	addHtml +=				'<textarea name="newCmtContent" id="new_cmt_content'+item.cmtSeq+'" class="modWrite_content">'+item.cmtContent+'</textarea>'
	addHtml += 		"</div>"
	
	addHtml +=			"<div>"
	addHtml +=				'<input type="button" class="cmt_btn" id="mod_btn'+item.cmtSeq+'" value="수정" onclick="modAjax('+item.cmtBoardPk+', '+item.cmtSeq+')">'
	addHtml +=				"&nbsp"
	addHtml +=				'<input type="button" class="cmt_btn" id="del_btn'+item.cmtSeq+'" value="취소" onclick="canModFrm('+item.cmtSeq+')">'
	addHtml +=			"</div>"
	addHtml +=		"</div>"
	
	return addHtml
}




// 댓글 삭제 부분
function delAjax(cmtBoardPk, cmtSeq) {
	if(confirm('삭제하시겠습니까?')) {
		fetch(`/cmt?cmtBoardPk=${cmtBoardPk}&cmtSeq=${cmtSeq}`, {
			method: 'delete'
		}).then(res => res.json())
		.then(result => {
			if(result === 1) {
				selCmtList()
			} else {
				alert('삭제 실패')
			}
		})
	}
} 


// 댓글 수정 부분
function modAjax(cmtBoardPk, cmtSeq) {
	
	var newCmtContentId = 'new_cmt_content' + cmtSeq
	var newCmtContentElem = document.getElementById(`${newCmtContentId}`)
	
	var param = {
		cmtBoardPk: cmtBoardPk,
		cmtSeq: cmtSeq,
		cmtContent: newCmtContentElem.value
	}
	
	fetch('/cmt', {
		method: 'put',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}).then(res => res.json())
	.then(result => {
		if(result === 1) {
			selCmtList()
		} else {
			alert('수정 실패')
		}
	})
	
}



