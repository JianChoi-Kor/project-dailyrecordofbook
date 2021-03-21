/**
 * 
 */








function liked(cmtSeq, loginUserPk) {
	
	console.log(loginUserPk)
	
	if(isNaN(loginUserPk)) {
		if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')) {
			location.href = '/user/login'
			return
		}
	} else {
		

		const iconElem = document.querySelector(`#heart-icon${cmtSeq}`)
		const spanElem = document.querySelector(`#liked-span${cmtSeq}`)
	
		const PRESSED_CLASS = "press"
		
  		iconElem.classList.toggle(PRESSED_CLASS)
 		spanElem.classList.toggle(PRESSED_CLASS)

		
		
	}
	

	
}









