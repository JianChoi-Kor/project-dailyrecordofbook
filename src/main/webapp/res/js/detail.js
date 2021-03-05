/**
 * 
 */

var data = document.querySelector('#pageInfo')

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
			alert('글이 삭제되었습니다.')
			location.href = `/board/list?category=${category}`
			return
		} else {
			alert('글을 삭제하지 못했습니다.')
			return
		}
	}
}