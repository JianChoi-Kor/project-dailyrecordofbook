<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${param.category == 1}">
		<h1 class="h1">카테고리 1</h1>
	</c:when>
	<c:otherwise>
		<h1 class="h1">카테고리 2</h1>
	</c:otherwise>

</c:choose>

<div class="category">
	<a href="/board/list?category=1"><input type="button" class="category_btn" value="category 1" ></a>
	&nbsp&nbsp&nbsp&nbsp
	<a href="/board/list?category=2"><input type="button" class="category_btn" value="category 2" ></a>
</div>

<c:if test="${sessionScope.loginUser != null}">
	<a href="/board/write?category=${param.category}">
		<input type="button" class="write_btn" value="글쓰기" >
	</a>
</c:if>


<div class="gallery">
	<c:forEach items="${requestScope.list}" var="item">
		<div>
            <a href="#">
                <h2><img class="listImg" src="/res/img/profile.jpg" alt=""></h2>
                <h4><c:out value="${pageScope.item.title}"/></h4>
                <p><c:out value="${pageScope.item.ctnt}"/></p>
                <h5>
                    <div class="profileImg">
					<c:choose>
						<c:when test="${sessionScope.loginUser.profileImg == null}">
							<c:set var="src" value="profile.jpg"/>
						</c:when>
						<c:otherwise>
							<c:set var="src" value="user/${sessionScope.loginUser.userPk}/${sessionScope.loginUser.profileImg}"/>
						</c:otherwise>	
					</c:choose>
						<img src="/res/img/${src}" alt="프로필 이미지">
					</div>
                    <p>{sessionScope.loginUser.userNm}</p>
                </h5>
            </a>
        </div>
	</c:forEach>
</div>





