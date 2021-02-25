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
		<div class="errMsg" id="errMsg"></div>
		<!-- <button class="login_btn">로그인</button> -->
		<input type="button" id="loginBtn" class="login_btn" value="로그인">
	</form>

	<div class="login_middle">
		<a href="/user/findEmail"><span>이메일 찾기</span></a><span> | </span> 
		<a href="/user/findPw"><span>비밀번호 찾기</span></a><span>  | </span>
		<a href="/user/terms"><span>회원가입</span></a>
	</div>
</div>