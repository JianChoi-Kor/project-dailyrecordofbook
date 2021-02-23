<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="changePw_content">
	<h1 class="h1">비밀번호 변경</h1>
	<form id="changePw_frm">
		<div class="changePw">
			<h3 class="h3">기존 비밀번호</h3>
			<input type="password" name="userPw" placeholder="기존 비밀번호를 입력해주세요.">
			<p></p>
			<h3 class="h3">새로운 비밀번호</h3>
			<input type="password" name="newUserPw" placeholder="새로운 비밀번호를 입력해주세요.">
			<p></p>
			<h3 class="h3">새로운 비밀번호 재입력</h3>
			<input type="password" name="newUserPwRe" placeholder="새로운 비밀번호를 다시 한 번 입력해주세요.">
		</div>
		<div class="errMsg" id="errMsg"></div>
		<input type="button" id="changePwBtn" class="changePw_btn" value="비밀번호 변경">
	</form>

</div>