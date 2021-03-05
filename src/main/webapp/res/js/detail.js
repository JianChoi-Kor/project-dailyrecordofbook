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
	
	function ajax() {
		var boardpk = pageInfo.dataset.boardpk
		
		fetch(`/board/del/${boardpk}`, {
			method: 'delete'
		})
	}
}