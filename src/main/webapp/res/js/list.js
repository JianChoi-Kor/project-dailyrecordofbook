/**
 * 
 */

// 페이지 로드시 클릭된 페이징 버튼에 스타일 주기 위한 javascript
window.onload = function() {
	const pageInfo = new URLSearchParams(location.search)	
	
	var pageNum = pageInfo.get('curPage')
	console.log(pageNum)
	
	
	var clickedElem = document.querySelector(`.span[data-page="${pageNum || 1}"]`)
	console.log(clickedElem)
	
	//var clickedElem = document.getElementById(clickedId)
	clickedElem.classList.add('clicked')
}
