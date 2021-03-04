<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="profileFram">
	<div class="imgFrame">
		<c:choose>
			<c:when test="${requestScope.data.profileImg == null}">
				<c:set var = "src" value="profile.jpg"/>
			</c:when>
			<c:otherwise>
				<c:set var="src" value="user/${requestScope.data.userPk}/${requestScope.data.profileImg}"/>
			</c:otherwise>	
		</c:choose>
		<img src="/res/img/${src}" alt="프로필 이미지">
	</div>

	<div class="innerFrm">
		<h3 class="h3">프로필 이미지 업로드</h3>
		<div>
			<input class="imgBtn" type="file" id="profileImg" accept="image/*">
			<input class="imgBtn" type="button" value="이미지 업로드" onclick="profileUpload()">
			<p class="p">* 1:1 비율의 사진을 업로드해 주세요.</p>
		</div>
	</div>
	
	<div class="innerFrm">
		<h3 class="h3">회원 정보</h3>
		<div class="detail"><label>이메일 : </label>${requestScope.data.userEmail}</div>
		<div class="detail"><label>이름 : </label>${requestScope.data.userNm}</div>
		<div class="detail"><label>전화번호 : </label>${requestScope.data.userPn}</div>
		<div class="detail"><label>가입일 : </label>${requestScope.data.regDt}</div>
	</div>
	
	<!-- 하드코딩 한 부분 나중에 DB랑 연동하여 작업 + 업로드 하는 방법도 생각 -->
	<div class="innerFrm">
		<h3 class="h3">참가한 모임</h3>
		<ul>
			<li class="detail">하루 독서모임 "브람스를 좋아하세요..." / 2020.12.03</li>
			<li class="detail">하루 독서모임 "맥베스" / 2020.12.13</li>
			<li class="detail">하루 독서모임 "시계태엽 오렌지" / 2021.03.01</li>
		</ul>
	</div>
	
	<div class="btnFrm">
		<input class="Btn changePwBtn" type="button" value="비밀번호 변경" onclick="location.href='/user/changePw'">
		<input class="Btn withDrawalBtn" type="button" value="&nbsp&nbsp&nbsp회원 탈퇴&nbsp&nbsp&nbsp" onclick="location.href='/user/withDrawal'">
	</div>



</div>
