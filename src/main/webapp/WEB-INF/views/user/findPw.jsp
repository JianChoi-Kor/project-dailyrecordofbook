<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="findPw_content">
	<h1 class="h1">비밀번호 찾기</h1>
	<form id="findPw_frm">
		<div class="findPw">
			<h3 class="h3">이메일</h3>
			<input type="text" name="userEmail" placeholder="이메일을 입력해주세요.">
			<p>- 가입하신 이메일로 임시 비밀번호가 발송됩니다.</p>
		</div>
		<div class="findPw">
			<h3 class="h3">이름</h3>
			<input type="text" name="userNm" placeholder="이름을 입력해주세요.">
		</div>
		<div class="findPw" id="underFindPw">
			<h3 class="h3">전화번호</h3>
			<input type="text" name="userPn" placeholder="전화번호를 입력해주세요.">
			<p>- 전화번호는 "-" 없이 입력해주세요. ex) 01099998888</p>
		</div>
		<div class="errMsg" id="errMsg"></div>
		<input type="button" id="findPwBtn" class="findPw_btn" value="비밀번호 찾기">
	</form>
</div>