<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="changePw_content">
	<h1 class="h1">비밀번호 변경</h1>
	<form id="changePw_frm">
		<div class="changePw">
			<!-- 비밀번호 유효성 검사를 위한 id 값을 hs에서 받아온다. -->
			<input type="hidden" name="userEmail" value="${sessionScope.loginUser.getUserEmail()}">

			 
			<h3 class="h3">기존 비밀번호</h3>
			<input type="password" name="userPw" placeholder="기존 비밀번호를 입력해주세요.">
			<h3 class="h3">새로운 비밀번호</h3>
			<input type="password" name="newUserPw" placeholder="새로운 비밀번호를 입력해주세요.">
			<p>- 비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.</p>
			<h3 class="h3">새로운 비밀번호 재입력</h3>
			<input type="password" name="newUserPwRe" placeholder="새로운 비밀번호를 다시 한 번 입력해주세요.">
		</div>
		<div class="errMsg" id="errMsg"></div>
		<input type="button" id="changePwBtn" class="changePw_btn" value="비밀번호 변경">
	</form>
</div>