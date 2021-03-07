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
		
		
		var html = ''

		html += '<div class="oneCmt">'
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
		
		
		
		// 자신이 쓴 댓글이라면 삭제, 수정버튼 추가
		var loginUserPk = parseInt(pageInfo.dataset.loginuserpk)
		console.log(loginUserPk)
		if(loginUserPk === item.writerPk) {
	
					html +=			"<div>"
					html +=				'<input type="button" class="cmt_btn cmt_btn1" value="수정">'
					html +=				"&nbsp"
					html +=				'<input type="button" class="cmt_btn" value="삭제">'
					html +=			"</div>"
		}
		
		html +=		"</div>"
		html +=	"</div>"
		
		return html
	
	}

}

selCmtList()
