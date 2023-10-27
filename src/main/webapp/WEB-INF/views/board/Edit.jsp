<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
</head>
<body>
<div
        class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block"
        style="width: 1000px;">
    <form name="writeFrm" method="post" enctype="multipart/form-data"
          action="/board/updateBoard.do" onsubmit="return validateForm(this);">
        <c:if test="${not empty map}">
            <input type="hidden" name="board_num" value="${ map.board_num }"> <input
                type="hidden" name="prevOfile" value="${ map.board_ofile }"> <input
                type="hidden" name="prevSfile" value="${ map.board_sfile }"> <input
                type="hidden" name="member_num" value="${ sessionScope.num }">

            <table class="table mb-4">
                <colgroup>
                    <col width="15%"/>
                    <col width="*"/>
                </colgroup>
                <tr>
                    <td>제목</td>
                    <td><input type="text" value="${ map.board_title }" class="form-control" name="board_title"></td>
                </tr>
                <tr>
                    <td>분류</td>
                    <td><select name="board_cate" class="form-select">
                        <c:if test="${ sessionScope.rank != 0 }">
                            <option value="notice">공지</option>
                        </c:if>
                        <option selected value="review">리뷰</option>
                        <option value="free">자유</option>
                        <option value="image">사진</option>
                        <option value="question">질문</option>
                    </select></td>
                </tr>
                <tr>
                    <td colspan="2"><textarea name="board_content" class="form-control"
                                              style="height: 300px;">${ map.board_content }</textarea></td>
                </tr>
                    <%--<tr>
                        <td>첨부 파일</td>
                        <td><input type="file" name="ofile" /></td>
                    </tr>--%>
            </table>

            <div class="row mb-4">
                <div class="col-8"></div>
                <div class="col ">
                    <button type="reset" class="btn btn-warning w-100"
                            style="text-align: center; font-size: 24px;">RESET
                    </button>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100"
                            style="text-align: center; font-size: 24px;">작성 완료
                    </button>
                </div>
            </div>
        </c:if>
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