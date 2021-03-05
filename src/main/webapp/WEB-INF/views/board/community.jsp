<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="list">

	<c:choose>
		<c:when test="${param.category == 11}">
			<h1 class="h1">책방일지 - 현재 진행중인 독서모임</h1>
		</c:when>
		<c:otherwise>
			<h1 class="h1">책방일지 - 종료, 마감된 독서모임</h1>
		</c:otherwise>

	</c:choose>

	<div class="category">
		<a href="/board/community?category=11"><input type="button"
			class="category_btn" value="진행중인 모임"></a> &nbsp&nbsp&nbsp&nbsp
		<a href="/board/community?category=12"><input type="button"
			class="category_btn" value="종료, 마감된 모임"></a>
	</div>


	<div class="btn">
		<c:if test="${sessionScope.loginUser.authStatus == 99}">
			<a href="/board/write?category=${param.category}"> <input
				type="button" id="write_btn" class="write_btn" value="글쓰기">
			</a>
		</c:if>
	</div>


	<div class="gallery">
		<c:forEach items="${requestScope.list}" var="item">
			<div class="content">
				<a
					href="/board/detail?category=${param.category}&boardPk=${pageScope.item.boardPk}">
					<div style="overflow: hidden">
						<h2>
							<img class="listImg" src="${pageScope.item.boardMainImg}" alt="">
						</h2>
					</div>
					<div class="listContent">
						<h4 class="listTitle">
							<c:out value="${pageScope.item.title}" />
						</h4>
						<h5>
							<div class="profileImg">
								<c:choose>
									<c:when test="${pageScope.item.profileImg == null}">
										<c:set var="src" value="profile.jpg" />
									</c:when>
									<c:otherwise>
										<c:set var="src"
											value="user/${pageScope.item.userPk}/${pageScope.item.profileImg}" />
									</c:otherwise>
								</c:choose>
								<img src="/res/img/${src}" alt="프로필 이미지">
							</div>
							<p class="listWriterNm">${pageScope.item.writerNm}</p>
						</h5>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	
	
	<div class="paging" id="pagingContent">
		<c:if test="${requestScope.paging.startPageInCurBlock != 1 }">
			<span class="span"> <a
				href="/board/community?category=${param.category}&curPage=${requestScope.paging.startPageInCurBlock - 1 }">&lt;</a>
			</span>
		</c:if>
		<c:forEach begin="${requestScope.paging.startPageInCurBlock}"
			end="${requestScope.paging.endPageInCurBlock}" step="1" var="page">
			<span class="span" data-page="${page}"><a
				href="/board/community?category=${param.category}&curPage=${page}">${page}</a></span>
		</c:forEach>
		<c:if test="${requestScope.paging.endPageInCurBlock != requestScope.paging.lastPage}">
			<span class="span"> <a
				href="/board/community?category=${param.category}&curPage=${requestScope.paging.endPageInCurBlock + 1 }">&gt;</a>
			</span>
		</c:if>
	</div>
	
	
</div>	
	