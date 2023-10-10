<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/boardLayout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PickCom:견적추천 커뮤니티</title>
</head>
<div
	class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block"
	style="width: 1000px;">
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="../board/edit.do" onsubmit="return validateForm(this);">
		<input type="hidden" name="idx" value="${ dto.board_no }" /> <input
			type="hidden" name="prevOfile" value="${ dto.board_ofile }" /> <input
			type="hidden" name="prevSfile" value="${ dto.board_sfile }" />

		<table class="table mb-4">
			<colgroup>
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<tr>
				<td>제목</td>
				<td><input type="text" value="${ dto.board_title }" class="form-control" name="title" /></td>
			</tr>
			<tr>
				<td>분류</td>
				<td><select name="cate" class="form-select">
						<c:if test="${ sessionScope.rank }">
							<option value="공지">공지</option>
						</c:if>
						<option selected value="리뷰">리뷰</option>
						<option value="자유">자유</option>
						<option value="팁&정보">팁&정보</option>
						<option value="질의응답">질의응답</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="content" class="form-control"
						style="height: 300px;">${ dto.board_content }</textarea></td>
			</tr>
			<tr>
				<td>첨부 파일</td>
				<td><input type="file" name="ofile" /></td>
			</tr>
		</table>

		<div class="row mb-4">
			<div class="col-8"></div>
			<div class="col ">
				<button type="reset" class="btn btn-warning w-100"
					style="text-align: center; font-size: 24px;">RESET</button>
			</div>
			<div class="col">
				<button type="submit" class="btn btn-primary w-100"
					style="text-align: center; font-size: 24px;">작성 완료</button>
			</div>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
	function validateForm(form) {
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
	}
</script>
</html>