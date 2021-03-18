<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 상단 슬라이드 부분 -->
<!-- media 바뀌는 기준으로 일반 슬라이드 변경 필요 -->
<div class="top-slide-container">
	<div class="slides">
		<div class="slide">
			<img src="/res/img/slide1.jpg" alt="">
		</div>
		<div class="slide">
			<img src="/res/img/slide2.jpg" alt="">
		</div>
		<div class="slide">
			<img src="/res/img/slide3.jpg" alt="">
		</div>
		<div class="slide">
			<img src="/res/img/slide4.jpg" alt="">
		</div>
	</div>
	<div class="slide-controls">
		<button id="prev-btn">
			<i class="fas fa-chevron-left"></i>
		</button>
		<button id="next-btn">
			<i class="fas fa-chevron-right"></i>
		</button>
	</div>
</div>




<!-- 아이콘 부분 -->
<div class="middle">
	<div class="middle-first">
		<i class="icon far fa-laugh"></i>
		<h3 class="h3">
			275<span>&nbsp명</span>
		</h3>
		<h6 class="h6">책방일지와 함께하신 분들</h6>
	</div>
	<div class="middle-second">
		<i class="icon fas fa-book-open"></i>
		<h3 class="h3">
			82<span>&nbsp권</span>
		</h3>
		<h6 class="h6">함께 읽은 책들</h6>
	</div>
	<div class="middle-third">
		<i class="icon far fa-comments"></i>
		<h3 class="h3">
			511<span>&nbsp개</span>
		</h3>
		<h6 class="h6">함께 나눈 발제들</h6>
	</div>
</div>

<div class="middle-etc">
	<h3>더욱 더 많은 사람들이 책과 가까워 지기를 바라며</h3>
	<h3>책방일지는 오늘도 우리의 일지를 써내려갑니다.</h3>
	<h4>2021년 2월 기준</h4>
</div>


<!-- 
<div class="book-slide-container">
	<div class="book-slides">
		<div class="book-slide">
			<img src="/res/img/11.jpg" alt="">
		</div>
		<div class="book-slide">
			<img src="/res/img/22.jpg" alt="">
		</div>
		<div class="book-slide">
			<img src="/res/img/33.jpg" alt="">
		</div>
		<div class="book-slide">
			<img src="/res/img/44.jpg" alt="">
		</div>
		<div class="book-slide">
			<img src="/res/img/55.jpg" alt="">
		</div>
	</div>
	
	<div class="book-slide-controls">
		<button id="book-prev-btn">
			<i class="fas fa-chevron-left"></i>
		</button>
		<button id="book-next-btn">
			<i class="fas fa-chevron-right"></i>
		</button>
	</div>
</div>
 -->
<div class="bookSlide">
	<div class="book-slide-container">
		<img src="/res/img/11.jpg" alt="">
		<img src="/res/img/22.jpg" alt="">
		<img src="/res/img/33.jpg" alt="">
		<img src="/res/img/44.jpg" alt="">
		<img src="/res/img/55.jpg" alt="">
		<img src="/res/img/11.jpg" alt="">
		<img src="/res/img/22.jpg" alt="">
		<img src="/res/img/33.jpg" alt="">
		<img src="/res/img/44.jpg" alt="">
		<img src="/res/img/55.jpg" alt="">
	</div>
</div>

<div class="book-slide-btn">
	<c:if test="${sessionScope.loginUser.authStatus == 99}">
		<a href="#"> 
			<input type="button" id="add_btn" class="add_btn" value="책 추가">
		</a>
	</c:if>
</div>

<!-- 문의 버튼 -->
<div class="inquiry">
	<input id="chkUser" type="hidden"
		value="${sessionScope.loginUser.userPk}"> <input type="button"
		id="inquiryBtn" class="inquiry_btn" value="문의하기">
</div>

<!-- 
<script src="/res/js/mainBookSlide.js"></script>
 -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="/res/js/bookSlide.js"></script>


<script src="https://kit.fontawesome.com/f094e80764.js"
	crossorigin="anonymous"></script>