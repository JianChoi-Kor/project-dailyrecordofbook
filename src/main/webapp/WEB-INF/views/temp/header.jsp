<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<header>
    <nav class="navbar">
        <div class="navbar_logo_up">
            <a href="/main/home">
                <img src="/res/img/logo_down.jpg">
            </a>
        </div>
        <div class="navbar_logo_down">
            <a href="">
                <img src="/res/img/logo_downdown.jpg">
            </a>
        </div>

        <ul class="navbar_menu">
            <li><a href="/main/home">책방일지는</a></li>
            <li><a href="/user/login">책방일지 독서 모임</a></li>
            <li><a href="/board/list?category=1">책방일지와 함께하며</a></li>
        </ul>

        <ul class="navbar_login">
        	<c:choose>
        		<c:when test="${sessionScope.loginUser == null}">
            		<li><a href="/user/login">로그인</a></li>
            		<li><a href="/user/terms">회원가입</a></li>
            	</c:when>
            	<c:otherwise>
            		<!-- mypage 만들어야 함 (안에 비밀번호 변경도) -->

					<div class="profileImg">
					<c:choose>
						<c:when test="${sessionScope.loginUser.profileImg == null}">
							<c:set var = "src" value="profile.jpg"/>
						</c:when>
						<c:otherwise>
							<c:set var="src" value="user/${sessionScope.loginUser.userPk}/${sessionScope.loginUser.profileImg}"/>
						</c:otherwise>	
					</c:choose>
						<img src="/res/img/${src}" alt="프로필 이미지">
					</div>
					
            		<li class="userInfo"><a href="/user/mypage">${sessionScope.loginUser.userNm}님 회원정보</a></li>
            		<li class="userInfo"><a href="/user/logout">로그아웃</a></li>
            	</c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>