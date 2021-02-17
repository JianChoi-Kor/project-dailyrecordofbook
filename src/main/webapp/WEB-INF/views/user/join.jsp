<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="join_content">
	<p class="sign">
	<h1 class="h1">회원가입</h1>
	</p>
	<form id="join_frm">

		<!-- 아이디 부분 -->
		<div class="join_email">
			<h3 class="h3">
				이메일 <span> * </span>
			</h3>
			<input type="text" name="userEmail" placeholder="이메일을 입력해주세요.">
			<p></p>
		</div>

		<!-- 비밀번호 부분 -->
		<div class="join_pw">
			<h3 class="h3">
				비밀번호 <span> * </span>
			</h3>
			<div class="left">
				<input type="password" name="userPw" placeholder="비밀번호를 입력해주세요.">
			</div>
			<h3 class="h3 h3_">
				비밀번호 확인 <span> * </span>
			</h3>
			<div class="left">
				<input type="password" name="userPwRe"
					placeholder="비밀번호를 다시 입력해주세요.">
			</div>
			<p>- 비밀번호는 8~16자, 영문, 숫자, 특수문자를 포함해야 합니다.</p>
		</div>

		<!-- 이름 부분 -->
		<div class="join_nm">
			<h3 class="h3">
				이름 <span> * </span>
			</h3>
			<div class="left">
				<input type="text" name="userNm" placeholder="이름을 입력해주세요.">
			</div>
			<p class="left left_">(책방일지는 실명으로 운영되고 있습니다. 꼭 한글 실명을 기입해주세요.)</p>
			<p class="left">- 띄어쓰기, 특수 문자 사용 불가</p>
		</div>

		<!-- 전화번호 부분 -->
		<div class="join_phone">
			<h3 class="h3">
				전화번호 <span> * </span>
			</h3>
			<div class="left">
				<input type="text" name="userPn" placeholder="전화번호를 입력해주세요.">
			</div>
			<p></p>
		</div>

		<!-- 추가 정보 -->
		<div class="join_info">
			<h3 class="h3">책방일지를 어떤 경로로 알게 되셨나요?</h3>
			<div class="left">
				<input type="text" name="searchInfo">
			</div>
			<p>예: 인스타그램, 네이버 블로그, 지인 추천 등</p>

			<h3 class="h3 h3_">한 달에 읽으시는 책은 몇 권 정도 되나요?</h3>
			<select class="join_rv" type="select" name="redingVolume">
				<option value class="">0권</option>
				<option value class="">1권</option>
				<option value class="">2권</option>
				<option value class="">3권</option>
				<option value class="">4권</option>
				<option value class="">5권 이상</option>
			</select>
		</div>
		
		<button class="join_btn">회원가입</button>
	</form>
</div>