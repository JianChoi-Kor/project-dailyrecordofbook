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

	<div>
		<h3>프로필 이미지 업로드</h3>
		<div>
			<input class="selectImgBtn" type="file" id="profileImg" accept="image/*">
		</div>
		<div>
			<input class="uploadImgBtn" type="button" value="업로드" onclick="profileUpload()">
		</div>
	</div>
	
	<div>이메일 : ${requestScope.data.userEmail}</div>
	<div>이름 : ${requestScope.data.userNm}</div>
	<div>전화번호 : ${requestScope.data.userPn}</div>
	<div>가입일 : ${requestScope.data.regDt}</div>
	
	<div>
		<input type="button" value="비밀번호 변경">
	</div>
	<div>
		<input type="button" value="회원탈퇴">
	</div>

</div>
