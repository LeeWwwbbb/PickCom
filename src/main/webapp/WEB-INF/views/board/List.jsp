<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/boardLayout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PickCom:견적추천 커뮤니티</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div
		class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block"
		style="width: 1000px;">
		<main>
			<!-- 검색 폼 -->
			<form action="search" method="get">
				<div class="row mb-4">
					<div class="col-2">
						<select name="searchField" class="form-select">
							<option value="userId">아이디</option>
							<option value="userName">닉네임</option>
							<option value="email">이메일</option>
							<option value="stat">상태</option>
						</select>
					</div>
					<div class="col-8">
						<input type="text" class="form-control" name="searchWord" />
					</div>
					<div class="col-2">
						<button type="submit" class="btn btn-primary w-100">검색</button>
					</div>
				</div>
			</form>

			<h2 class="mb-4">
				<span>${ cate } 게시판</span>
			</h2>

			<table border="1" width="90%" class="table">
				<thead class="table-light">
					<tr align="center">
						<th width="10%" scope="col">분류</th>
						<th width="*" scope="col" align="left" style="text-align: left;">제목</th>
						<th width="15%" scope="col">작성자</th>
						<th width="15%" scope="col">작성일</th>
						<th width="8%" scope="col">조회수</th>
						<th width="8%" scope="col">추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ not empty noticeList }">
							<c:forEach items="${ noticeList }" var="item">
								<tr align="center" class="table-secondary">
									<td>${ item.board_category }</td>
									<td align="left"><a
										href="../board/view.do?idx=${ item.board_no }">${ item.board_title }</a>
									</td>
									<td>${ item.member_name }</td>
									<td>${ item.board_createDate }</td>
									<td>${ item.board_visitCount }</td>
									<td>${ item.like_count }</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${ empty boardLists }">
							<tr>
								<td colspan="6" align="center">작성한 게시글이 없습니다</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ boardLists }" var="row" varStatus="loop">
								<tr align="center">
									<td>${ row.board_category }<input type="hidden"
										value="${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}" />
									</td>
									<td align="left"><a
										href="../board/view.do?idx=${ row.board_no }">${ row.board_title }</a>
									</td>
									<td>${ row.member_name }</td>
									<td>${ row.board_createDate }</td>
									<td>${ row.board_visitCount }</td>
									<td>${ row.like_count }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<!-- 하단 메뉴(바로가기, 글쓰기) -->
			<div class="row mb-4">
				<div class="col-2"></div>
				<div class="col-8 text-center">${ map.pagingImg }</div>
				<div class="col-2">
					<button type="button" class="btn btn-primary w-100"
						onclick="location.href='../board/write.do';">글쓰기</button>
				</div>
			</div>
			<table border="1" width="90%">
				<tr align="center">
					<td></td>
					<td width="100"></td>
				</tr>
			</table>
		</main>
	</div>
</body>
</html>