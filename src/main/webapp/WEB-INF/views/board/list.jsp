<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div>
	<a href="/board/list?category=1"><input type="button" class="category_btn" value="category 1" ></a>
	<a href="/board/list?category=2"><input type="button" class="category_btn" value="category 2" ></a>
</div>

<a href="/board/write?category=${param.category}"><input type="button" class="write_btn" value="글쓰기" ></a>
