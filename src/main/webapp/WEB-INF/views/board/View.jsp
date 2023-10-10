<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/boardSide.jsp"%>
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
			<a href="/../board/cateList.do?cate=${ dto.board_category }"
				class="nav-link px-10" style="font-size: 24px; font-weight: bold;">${ dto.board_category }
				게시판</a>
			<table class="table mb-4">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>

				<!-- 게시글 정보 -->
				<tr>
					<td>번호</td>
					<td>${ dto.board_no }</td>
					<td>작성자</td>
					<td>${ dto.member_name }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${ dto.board_createDate }</td>
					<td>조회수</td>
					<td>${ dto.board_visitCount }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${ dto.board_title }</td>
				</tr>
				<tr>
					<td colspan="4" height="300">${ dto.board_content }</td>
				</tr>
				<!-- 첨부파일 -->
				<tr>
					<td>첨부파일</td>
					<td colspan="3"><c:if test="${ not empty dto.board_ofile }">
            ${ dto.board_ofile }
            <a
								href="../board/download.do?ofile=${ dto.board_ofile }&sfile=${ dto.board_sfile }&idx=${ dto.board_no }">
								[다운로드] </a>
						</c:if></td>
				</tr>
			</table>

			<!-- 하단 메뉴(버튼) -->
			<div class="row mb-4">
				<div class="col-2">
					<c:if
						test="${ sessionScope.rank || dto.member_name == sessionScope.name }">
						<button type="button" class="btn btn-danger w-100"
							onclick="location.href='pass.do?mode=delete&idx=${ dto.board_no }';">
							삭제하기</button>
					</c:if>
				</div>
				<div class="col text-end">
					<a href="../board/like.do?idx=${ dto.board_no }"> <c:if
							test="${ like }">
							<img src="../img/likeTrue.png" alt=""
								style="width: 40px; height: 40px" />
						</c:if> <c:if test="${ !like }">
							<img src="../img/likeFalse.png" alt=""
								style="width: 40px; height: 40px" />
						</c:if>
					</a>
				</div>
				<div class="col-2">
					<c:if
						test="${ sessionScope.rank || dto.member_name == sessionScope.name }">
						<button type="button" class="btn btn-success w-100"
							onclick="location.href='pass.do?mode=edit&idx=${ dto.board_no }';">수정</button>
					</c:if>
				</div>
			</div>

			<hr class="mb-4">

			<table class="table mb-4">
				<colgroup>
					<col width="15%" />
					<col width="60%" />
					<col width="*" />
				</colgroup>
				<c:choose>
					<c:when test="${ not empty commentList }">
						<c:forEach items="${ commentList }" var="row">
							<tr align="center">
								<td>${ row.member_name }</td>
								<td align="left">${ row.comment_content }</td>
								<td>${ row.comment_date }</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>

			<c:if test="${ sessionScope.name != null }">
			<p>${ sessionScope.name }</p>
				<div class="border border-secondary w-100"
					style="padding: 10px; height: 150px;">
					<form action="/board/commentWrite.do" method="post">
					<input type="hidden"  name="boardNum" value="${dto.board_no }"/>
						<textarea class="form-control  w-100 p-3" rows="4" cols="50" style="height: 100px; resize: none;" name="content" ></textarea>
						<div class="text-end">
							<button type="submit" class="btn btn-outline-primary">등록</button>
						</div>
					</form>
				</div>
			</c:if>
		</main>
	</div>
</body>
</html>
