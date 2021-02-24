<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="withDrawal_content">
	<h1 class="h1">회원 탈퇴</h1>
	<form id="withDrawal_frm">
		<div class="withDrawal">
			<h3 class="h3">비밀번호</h3>
			<input type="password" name="userPw" placeholder="비밀번호를 입력해주세요.">
		</div>
		<div class="errMsg" id="errMsg"></div>
		<input type="button" id="withDrawalBtn" class="withDrawal_btn" value="회원탈퇴">
	</form>
</div>