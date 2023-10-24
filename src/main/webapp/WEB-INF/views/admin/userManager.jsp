<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<html>
<head>
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
			class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block"
			style="width: 1200px;">
			<!-- 사용자 검색 -->
			<form action="/admin/userList.do" method="get">
				<div class="row mb-4">
					<div class="col-2">
						<select name="searchField" class="form-select">
							<option value="member_id">아이디</option>
							<option value="member_name">닉네임</option>
							<option value="member_email">이메일</option>
							<option value="member_stat">상태</option>
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

			<!-- 사용자 테이블 -->
			<div>
				<table width="90%" class="table table-hover">
					<tr align="center">
						<th width="12%">아이디</th>
						<th width="8%">닉네임</th>
						<th width="12%">이메일</th>
						<th width="8%">상태</th>
						<th width="12%">상태해제일</th>
						<th width="*">사유</th>
						<th width="12%">가입일</th>
						<th width="12%">관리</th>
					</tr>
					<c:choose>
						<c:when test="${ empty userLists }">
							<tr>
								<td colspan="8" align="center">유저가 한명도 없습니다</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ userLists }" var="row" varStatus="loop">
								<tr align="center">
									<td>${ row.member_id }<input type="hidden"
										value="${ row.member_no } }" />
									</td>
									<td><input type="text" value="${ row.member_name }"
										class="form-control" id="userName" /></td>
									<td>${ row.member_email }</td>
									<td>${ row.member_stat }</td>
									<td>${ row.member_statDate }</td>
									<td>${ row.member_reason }</td>
									<td>${ row.member_regDate }</td>
									<td><button type="button" class="btn btn-primary w-100"
											data-bs-toggle="modal" data-bs-target="#modal"
											onclick="setSelectedMemberNo(${ row.member_no });">삭제</button></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
				<div class="row mb-4">
					<div class="col text-center">${ map.pagingImg }</div>
				</div>
			</div>

			<input type="hidden" name="idx" id="idx"/>

			<!-- 사용자 컨트롤 -->
			<form action="/admin/userManage.do" method="get">
				<div class="row">
					<div class="col-2">
						<select name="statDays" class="form-select">
							<option value="3">3일</option>
							<option value="7">7일</option>
							<option value="30">1개월</option>
							<option value="365">1년</option>
						</select>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" id="reason" name="reason"
							placeholder="정지 사유" />
					</div>
					<div class="col-2">
						<button type="submit" class="btn btn-danger w-100">정지</button>
					</div>
					<div class="col-1"></div>
					<div class="col-2"></div>
					<div class="col-2"></div>
				</div>
			</form>

		</div>

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
						<button type="button" data-bs-dismiss="modal" class="btn btn-primary"  id="userDeleteBtn"
						onclick="location.href='/admin/memberDelete.do?idx=' + document.getElementById('idx').value;">확인</button>
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
		var selectedMemberNo = null;
    $(document).ready(function() {

        $("table").on("click", "td", function() {
            var memberNo = $(this).closest("tr").find("input[type='hidden']").val();
            selectedMemberNo = memberNo;
        });

        $("#userManageForm").submit(function(event) {
            event.preventDefault();

            if (selectedMemberNo !== null) {
                $(this).append("<input type='hidden' name='num' value='" + selectedMemberNo + "'>");
                this.submit();
            }
        });
    });

    function setSelectedMemberNo(memberNo) {
		document.getElementById("idx").value = memberNo;
	}

/*
    function updateMemberName(userNo, userName) {
        $.ajax({
            url: "/admin/memberUpdate.do",
            method: "post",
            data: {
                number: userNo
                nickName: userName
            },
            success: function(result) {
                var responseJson = JSON.parse(result);
                if (responseJson.isDuplicate) {
                	alert("사용자 정보 삭제 실패");
                } else {
                	alert("사용자 정보 삭제 성공");
                }
            },
            error: function(xhr) {
                alert("에러코드 = " + xhr.status);
            }
        });
    }

    $(document).ready(function() {
    	var selectedMemberNo = null;

        function setSelectedMemberNo(memberNo) {
            selectedMemberNo = memberNo;
        }

        $("button[data-bs-target='#modal']").click(function() {
            var memberNo = $(this).attr("data-member-no");
            setSelectedMemberNo(memberNo);
        });
        $("#userDeleteBtn").click(function() {
        $.ajax({
            url: "/admin/memberDelete.do",
            method: "post",
            data: {
                number: selectedMemberNo
            },
            success: function(result) {
                var responseJson = JSON.parse(result);
                if (responseJson.isDuplicate) {
                	alert("사용자 정보 삭제 실패");
                } else {
                	alert("사용자 정보 삭제 성공");
                }
            },
            error: function(xhr) {
                alert("에러코드 = " + xhr.status);
            }
        });
		});
	}); */
</script>
	</main>
</body>
</html>