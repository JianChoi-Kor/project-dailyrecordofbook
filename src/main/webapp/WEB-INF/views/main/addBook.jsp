<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://cdn.ckeditor.com/ckeditor5/25.0.0/classic/ckeditor.js"></script>

<div class="write_content">

		<h1 class="h1">북 슬라이드 목록 추가하기</h1>


	<form id="write_frm" action="/main/addBook" method="post" onsubmit="return chk()">
		
		<div>
			<input type="text" class="write_title" name="bookTitle" placeholder="도서 제목을 입력해주세요." required>
		</div>
		<div>
			<input type="text" class="write_title" name="communityInfo" placeholder="모임 명과 날짜를 입력해주세요. (5기 정기 독서모임 / 2021.03.18)" required>
		</div>
		<textarea name="bookImg" id="editor" placeholder="이미지만 넣어주세요." required>
		</textarea>
		<div>
			<input type="submit" class="write_btn" value="추가">
		</div>
	</form>
</div>


<!-- ckeditor js -->
<script src="/res/js/ckeditor.js?ver=2"></script>



<!-- 
<div class="write_content">
	<h1 class="h1">책방일지와 함께하며</h1>
	<form id="write_frm" action="/board/write" method="post" onsubmit="return chk()">
		<input type="hidden" name="category" value="${param.category}">
		<div>
			<input type="text" class="write_title" name="title" placeholder="제목을 입력해주세요." required>
		</div>
		<textarea name="content" id="editor" placeholder="내용을 입력해주세요."></textarea>
		<div>
			<input type="submit" class="write_btn" value="등록">
		</div>
	</form>
</div>
 -->

 
 
 