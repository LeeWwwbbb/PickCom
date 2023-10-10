<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/layout/myLayout.jsp"%>
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
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<div
		class="position-absolute top-50 start-50 translate-middle h-50 d-inline-block"
		style="width: 1000px;">
		<div class="row mb-4">
			<div class="col-2">
				<label for="userId" class="form-label">아이디</label>
			</div>
			<div class="col-7">
				<input type="text" id="userId" value="${ dto.member_id }"
					class="form-control" readonly />
			</div>
			<div class="col"></div>
		</div>
		<div class="row mb-4">
			<div class="col-2">
				<label for="userName" class="form-label">닉네임</label>
			</div>
			<div class="col-7">
				<input type="text" id="userName" value="${ dto.member_name}"
					class="form-control" />
			</div>
			<div class="col">
				<button type="button" class="btn btn-secondary w-100"
					onclick="location.href='/my/nameChange.do?userName=' + document.getElementById('userName').value;">닉네임 변경</button>
			</div>
		</div>
		<div class="row mb-4">
			<div class="col-2">
				<label for="email" class="form-label">이메일</label>
			</div>
			<div class="col-7">
				<input type="email" class="form-control"
					value="${ dto.member_email }" id="email" name="email" readonly>
			</div>
			<div class="col"></div>
		</div>
		<div class="row mb-4">
			<div class="col-6"></div>
			<div class="col">
				<button type="button" class="btn btn-primary w-100"
					onclick="location.href='/member/my/pw_change.jsp';">비밀번호
					변경</button>
			</div>
			<div class="col">
				<button type="button" data-bs-toggle="modal" data-bs-target="#modal" class="btn btn-danger w-100" 
					data-bs-toggle="modal">회원 탈퇴</button>
			</div>
		</div>
	</div>

	<input type="hidden" name="idx" id="idx" />

	<div class="modal" id="modal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">사용자 삭제</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>사용자 정보를 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" data-bs-dismiss="modal" class="btn btn-primary" id="userDeleteBtn"
						onclick="location.href='/my/memberDelete.do';">확인</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function changeName() {
			var userName = document.getElementById('userName').value;

			var namePattern = /^[가-힣A-Za-z0-9]{2,8}$/;
			if (namePattern.test(userName)) {
				$.ajax({
					url : '../my/nameChange.do',
					type : 'POST',
					data : {
						userName : userName
					},
					success : function(data) {
						$('#modalGood .modal-body p').text(data);
						$('#modalGood').modal('show');
					},
					error : function() {
						alert('요청 실패');
					}
				});
			} else {
				alert('이름은 한글, 영문 대소문자, 숫자로 구성되어야 하며 2자에서 8자까지 입력 가능합니다.');
			}
		}

		function memberDelete() {
			var memberNo = "${ row.member_no }";

			$.ajax({
				url : "../admin/memberDelete.do",
				type : "POST",
				data : {
					num : memberNo
				},
				success : function(data) {
					alert(data);
					// 여기서 적절한 알림 창을 띄워주세요
				},
				error : function() {
					alert("요청 실패");
				}
			});
		}
	</script>
</body>
</html>