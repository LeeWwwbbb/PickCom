<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<header class="p-3 text-bg-dark">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<a href="../main.jsp">
						<img src="../../img/header_logo.png" alt="로고">
					</a>
					<c:if test="${ sessionScope.num != null }">
					</c:if>
				</ul>

				<div class="row">
					<div class="col text-center">
						<c:if test="${ sessionScope.num != null }">
							<p class="text-white " style="font-size: 16px;">${ sessionScope.name } 님</p>
						</c:if>
					</div>
					<div class="col-2"></div>
				</div>
				<div class="text-end">
					<c:if test="${ sessionScope.num != null }">
						<c:if test="${ sessionScope.rank }">
							<button type="button" class="btn btn-outline-light me-2"
								onclick="location.href='/../admin/userList.do';">관리페이지</button>
						</c:if>
						<c:if test="${ !sessionScope.rank }">
							<button type="button" class="btn btn-outline-light me-2"
								onclick="location.href='/../my/myPage.do';">My Page</button>
						</c:if>
						<button type="button" class="btn btn-outline-light me-2"
							onclick="location.href='/../login/logout.do';">Logout</button>
					</c:if>
					<c:if test="${ sessionScope.num == null }">
						<button type="button" class="btn btn-outline-light me-2"
							onclick="location.href='/../../member/login/login.jsp';">Login</button>
						<button type="button" class="btn btn-warning"
							onclick="location.href='/../../member/join/join_terms.jsp';">Sign-up</button>
					</c:if>
				</div>
			</div>
		</div>
	</header>
</body>
</html>