<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="findEmail_content">
	<h1 class="h1">아이디 찾기</h1>
	<form id="findEmail_frm">
		<div class="findEmail">
			<h3 class="h3">이름</h3>
			<input type="text" name="userNm" placeholder="이름을 입력해주세요.">
		</div>
		<div class="findEmail" id="underFindEmail">
			<h3 class="h3">전화번호</h3>
			<input type="text" name="userPn" placeholder="전화번호를 입력해주세요.">
			<p>- 전화번호는 "-" 없이 입력해주세요. ex) 01099998888</p>
		</div>
		<div class="errMsg" id="errMsg"></div>
		<input type="button" id="findEmailBtn" class="findEmail_btn" value="아이디 찾기">
	</form>
</div>