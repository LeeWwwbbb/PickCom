<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/layout/header.jsp"%>
<%@ include file="/layout/footer.jsp"%>
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
	<main>
		<div
			class="position-absolute top-50 start-50 translate-middle h-50 d-inline-block" style="width: 1000px;">
			<form action="/my/pwChange.do" method="post" name="pwChangeForm"
				onsubmit="return validateForm(this);">
				<div class="row mb-4">
					<div class="col-2">
						<label for="pwd1" class="form-label">비밀번호 <span>*</span></label>
					</div>
					<div class="col">
						<input class="form-control"
							pattern="^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,16}$"
							name="pwd1" id="pwd1" type="password" required>
						<div class="row gx-1">
							<div class="col">
								<p>알파벳, 숫자, 특수문자를 조합한 8자 이상 16자 이하로 작성</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row mb-4">
					<div class="col-2">
						<label for="pwd2" class="form-label">비밀번호 확인 <span>*</span></label>
					</div>
					<div class="col">
						<input class="form-control" id="pwd2" type="password" required>
						<div class="row gx-1">
							<div class="col">
								<p id="confirmPasswordError" class="error"></p>
							</div>
						</div>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary" id="joinBtn" style="width: 300px; float: right;">비밀번호 변경</button>
				</div>
			</form>
		</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script>
		function validateForm(form) {
			var passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
			if (form.pwd1.value == "") {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			if (form.pwd2.value == "") {
				alert("비밀번호 확인을 입력하세요.");
				return false;
			}
			if (!passwordPattern.test(form.pwd1.value)) {
				alert("비밀번호는 8자에서 16자 사이의 영문 대소문자, 숫자, 특수문자(!@#$%^&+=)를 포함해야 합니다.");
				return false;
			}
			if (form.pwd1.value !== form.pwd2.value) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			return true;
		}

		$(document).ready(function() {
			// 비밀번호 확인 필드의 값이 변경될 때마다 비밀번호와 일치하는지 확인하고 오류 메시지를 업데이트
			$("#pwd2").on("input", function() {
				var password = $("#pwd1").val();
				var confirmPassword = $(this).val();
				var errorMessage = "비밀번호가 일치하지 않습니다.";

				if (password !== confirmPassword) {
					$("#confirmPasswordError").text(errorMessage);
				} else {
					$("#confirmPasswordError").text("");
				}
			});
		});
	</script>
</body>
</html>