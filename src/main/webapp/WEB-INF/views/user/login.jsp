<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="login_content">
	<h1 class="h1">로그인</h1>
	<form id="login_frm">
		<div class="login">
			<h3 class="h3">이메일</h3>
			<input type="text" name="userEmail" placeholder="이메일을 입력해주세요.">
			<p></p>
			<h3 class="h3">비밀번호</h3>
			<input type="password" name="userPw" placeholder="비밀번호를 입력해주세요.">
		</div>
		<button class="login_btn">로그인</button>
	</form>
	<div class="login_middle">
		<a href=""><span>이메일 찾기</span></a><span> | </span> 
		<a href=""><span>비밀번호 찾기</span></a><span>  | </span>
		<a href=""><span>회원가입</span></a>
	</div>
</div>