<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="detail_container">
	<h1 class="h1">책방일지와 함께하며</h1>
	<input type="hidden" value="${param.category}">
	<input type="hidden" id="pageInfo" data-loginuserpk="${sesseionScope.loginUser.userPk}" 
		data-boardpk="${param.boardPk}" data-category="${param.category}">
	
	<div class="btn">
		<c:if test="${sessionScope.loginUser.userPk == requestScope.data.userPk}">
			<a href="/board/mod?category=${param.category}&boardPk=${requestScope.data.boardPk}"> <input
				type="button" id="mod_btn" class="detail_btn" value="수정">
			</a>
			<input type="button" id="del_btn" class="detail_btn" value="삭제">
			
			<c:if test="${sessionScope.loginUser.authStatus == 99 && param.category == 11}">
				<input type="button" id="close_btn" class="detail_btn" value="모집 마감 변경">
			</c:if>
		</c:if>
	</div>

	
	<div class="detail_top">
		<div class="detail_title">${requestScope.data.title}</div>
		<div class="detail_writerInfo">
			<img id="writer_profile_img" src="/res/img/user/${requestScope.data.userPk}/${requestScope.data.profileImg}">
			<div id="writer_nm">${requestScope.data.writerNm}</div>
		</div>
		<div>${requestScope.data.regDt}</div>
	</div>
	
	<div class="detail_top">
		<div class="detail_content">${requestScope.data.content}</div>
	</div>
	
</div>