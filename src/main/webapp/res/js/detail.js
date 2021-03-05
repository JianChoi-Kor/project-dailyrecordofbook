/**
 * 
 */

var data = document.querySelector('#pageInfo')

// 글 삭제 버튼 ajax
var del_btnElem = document.querySelector('#del_btn')

if(del_btnElem) {
	del_btnElem.addEventListener('click', function() {
		if(confirm('삭제하시겠습니까?')) {
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
		if(result == 1) {
			
			if(category > 10) {
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

if(close_btnElem) {
	close_btnElem.addEventListener('click', function() {
		if(confirm('모집 상태를 모집마감으로 변경하시겠습니까?'))
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
		if(result == 1) {
			alert('모집 마감으로 변경되었습니다.')
			location.href = `/board/community?category=${category}`
			return
		} else {
			alert('상태 변경에 실패했습니다.')
			return
		}
	}
}




